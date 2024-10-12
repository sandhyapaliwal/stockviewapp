STOCKVIEW APP

Overview
StockView App is an Android application that allows users to search for stock information by entering stock symbols.
The app fetches data from the Alpha Vantage API and displays key stock metrics, including price and percentage change.

Features
Search Stock Symbols: Users can input stock symbols (eg- AAPL, TSLA, AMZN, GOOGL ETC.) to fetch information.
Real-time Data: Retrieves live stock data from the Alpha Vantage API.
User-Friendly Interface: Designed with a clean and intuitive layout for easy navigation.
NOTE- Some APIs, including Alpha Vantage, allow anonymous access or provide a default API key for limited requests. 
It is advised to get your free key, place in code then search for stock to get latest data.
 
Prerequisites
Android Studio
Kotlin support in Android Studio
Access to the Alpha Vantage API (consider using an API key for more reliable requests). 

API Key
Sign up at Alpha Vantage to obtain an free API key. Place the key in "YOUR_API_KEY" in your StockRepository.kt file.

Android Compatibility
Minimum SDK Version: 23 (Android 6.0, Marshmallow)
Tested on Devices: The app has been tested on various devices, including Pixel 5, medium size desktop.

Installation
Clone the repository:
1- Clone the repositary: git clone "https://github.com/yourusername/stockviewapp.git"
3- Open the project in Android Studio.
4- Sync the Gradle files to download the required dependencies.
5- Run the app on an Android device or emulator. 

**File Structure** 
All necessary files for the StockView App have been uploaded to the repository. 
Users can ignore certain files that may not be relevant for their needs, focusing on the main files related to functionality and implementation. 

MAIN FILES-
1- MainActivity.kt: The entry point of the app, handling user interactions and displaying stock information.
2- StockApiService.kt: Defines the API endpoints and methods for fetching stock data from the Alpha Vantage API.
3- StockData.kt: Models the data structure for the stock information retrieved from the API.
4- StockRepository.kt: Manages data operations, providing a clean API for data access to the rest of the application.
5- StockViewModel.kt: Serves as the communication center between the UI and the repository, handling data processing and business logic.
6- activity_main.xml: The layout file for the main activity, defining the user interface elements.
7- build.gradle.kts (project): The top-level Gradle build file containing configuration options common to all modules.
8- build.gradle.kts (module: app): Contains the application-specific build configurations and dependencies for the app module.
9- settings.gradle.kts: Manages the Gradle settings and project structure, including included modules.
10- styles.xml: Defines the app's styles, including themes and button styles.
11- attrs.xml: Contains custom attributes used in custom views within the app.
12- colors.xml: Defines color resources used throughout the app for a consistent color scheme.
13- strings.xml: Stores string resources for easy localization and management of text within the app.
14- themes.xml: Defines themes for the app, allowing customization of the overall appearance.
15- ic-launcher-background.xml: Defines the background drawable for the app's launcher icon.
16- ic-launcher-foreground.xml: Defines the foreground drawable for the app's launcher icon.
17- Mipmap Files: Contains various resolution versions of the launcher icon for different device densities.
