# FetchRewards Android App

## Overview
FetchRewards is an Android application designed to display and manage rewards data fetched from a remote API. Built with Kotlin, the app follows the MVVM architecture and Clean Architecture principles for a robust, maintainable, and testable codebase.

## Features
- Fetch and display a list of rewards from an API.
- Group, filter, and sort rewards data for better user experience.
- Modern UI using Jetpack Compose.
- Dependency injection with Hilt.
- Error handling and loading states.
- Reactive data streams using Kotlin Flow.

## Project Structure
The app is organized into the following layers:

### 1. **Data Layer**
- **`data/mappers`**: Handles transformations between DTOs and domain models.
- **`data/model`**: Defines data transfer objects (DTOs).
- **`data/remote`**: Contains API service definitions.
- **`data/repository`**: Implements repository interfaces.

### 2. **Domain Layer**
- **`domain/model`**: Defines domain entities.
- **`domain/repository`**: Contains repository interfaces.
- **`domain/use_case`**: Encapsulates business logic.

### 3. **Presentation Layer**
- **`presentation/ui`**: Contains UI components, screens, and states.
- **`presentation/viewmodel`**: Handles UI-related data using the `RewardViewModel`.

### 4. **Common Utilities**
- **`utils`**: Provides utility classes like `Resource` for handling success/error states.

## Key Components

### Technologies Used
- **Kotlin**: Programming language.
- **Jetpack Compose**: For building modern UI.
- **Hilt**: Dependency injection framework.
- **Retrofit**: For network calls.
- **MVVM**: Architectural pattern.
- **Coroutines**: For asynchronous programming.
- **Flow**: For managing reactive data streams.

### Important Files
- `FetchRewardApplication.kt`: Application class.
- `ApiService.kt`: API interface for network communication.
- `RewardViewModel.kt`: ViewModel for managing UI state.
- `RewardScreen.kt`: Compose UI for displaying rewards.

## Screenshots
![App Screenshot 1](screen_shot1.PNG)
![App Screenshot 2](screen_shot2.PNG)


## Setup Instructions

### Prerequisites
- Android Studio Electric Eel or later.
- JDK 11 or higher.
- Internet connection to fetch dependencies.

### Steps
1. Clone this repository:
   ```bash
   git clone https://github.com/kcbharat100/FetchRewards.git
   ```
2. Open the project in Android Studio.
3. Sync the Gradle files.
4. Run the app on an emulator or a physical device.

## Configuration
- **API Base URL**: Configure the API endpoint in `NetworkModule.kt`.
- **ProGuard Rules**: Modify `proguard-rules.pro` for release builds.

## Testing
- **Unit Tests**: todo
- **Instrumentation Tests**: todo
