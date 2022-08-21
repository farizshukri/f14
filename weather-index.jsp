<!DOCTYPE html>
<html>
<head>
    <title>Weather Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 20px;
        }
        .weather-report {
            display: inline-block;
            text-align: left;
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .weather-report h2 {
            margin-top: 0;
        }
        .weather-report p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
    <h1>Weather Information</h1>
    <div class="weather-report">
        <h2>Current Weather</h2>
        <p id="temperature">Temperature: --</p>
        <p id="humidity">Humidity: --</p>
        <p id="rain">Rain: --</p>
        <p id="wind">Wind Direction: --</p>
    </div>
    <script>
        async function fetchWeather() {
            try {
                const response = await fetch('/weather');
                const data = await response.json();
                
                document.getElementById('temperature').innerText = 'Temperature: ' + data.main.temp + ' °C';
                document.getElementById('humidity').innerText = 'Humidity: ' + data.main.humidity + '%';
                document.getElementById('rain').innerText = 'Rain: ' + (data.rain ? data.rain['1h'] + ' mm' : 'No data');
                document.getElementById('wind').innerText = 'Wind Direction: ' + data.wind.deg + '°';
            } catch (error) {
                console.error('Error fetching weather data:', error);
            }
        }
        
        fetchWeather();
    </script>
</body>
</html>
