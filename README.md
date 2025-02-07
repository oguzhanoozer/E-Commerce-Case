# E-Commerce Android App Case Study
A Android application sample developed using MVVM architecture, Dependency Injection, and clean code principles.

# 📁 Project Structure
```
com.ozi.acase/
├── data/
│   ├── adapter/
│   │   ├── ProductAdapter
│   │   └── SliderAdapter
│   ├── api/
│   │   ├── ApiService
│   │   ├── BaseService
│   │   └── MockService
│   ├── model/
│   │   ├── MockData
│   │   └── Product.kt
│   ├── repository/
│   │   └── ProductRepository
│   └── viewModel/
│       ├── DetailViewModel
│       └── MainViewModel
├── di/
│   ├── CaseApplication
│   └── NetworkModule
├── extension/
│   └── DialogExtension.kt
├── ui/
│   ├── detail/
│   │   └── DetailActivity
│   └── main/
│       └── MainActivity.kt
└── utils/
    ├── Constants
    └── Result
```

# Installation
<ul>
  <li>
  Clone the repository
  
  ```  
    git clone https://github.com/oguzhanoozer/E-Commerce-Case.git
  ```
  </li>

   <li> 
Open project with Android Studio

```
# Open Android Studio and select:
File -> Open -> Select the project directory

```
Build & Run
<li>

```
# From Android Studio:
Run 'app' configuration

# Or from command line:
./gradlew assembleDebug
```
</li>

Running Tests
<li>

```
# Run all tests:
./gradlew test

# Run specific test class:
./gradlew test --tests "com.ozi.acase.ProductRepositoryTest"
```
</li>

## API
<ul>
<li>The project uses Fake Store API:</li>
<li>Base URL: https://fakestoreapi.com</li>
<li>Endpoints:</li>
<li>/products - Product list</li>
<li>/products/{id} - Product detail</li>
</ul>

### Note About API Security
For this case study, API paths and endpoints are exposed in the codebase.

## Architecture Overview
<ul>
<li>data: Core data handling with adapters, API services, models and repository</li>
<li>di: Dependency injection modules with Hilt</li>
<li>extension: Kotlin extensions for UI components</li>
<li>ui: Activities and UI related components</li>
<li>utils: Helper classes and constants</li>
</ul>

## Implementation Details
Built with a focus on clean architecture and testability:
<ul>
  <li>NetworkManager for handling API requests and responses</li>
 <li>Pagination implementation for efficient data loading</li>
 <li>Constants management for maintainable codebase</li>
 <li>Clean code practices with minimal comments</li>
 <li>Mock Service structure for reliable testing</li>
 <li>Unit tests for repository layer</li>
</ul>

## Future Improvements
The app can be enhanced with:
</ul>
 <li>Advanced caching with Room Database</li>
 <li>Improved loading states with shimmer effects</li>
 <li>Paging 3 library implementation</li>
 <li>Better error handling and retry mechanisms</li>
 <li>Enhanced UI/UX for loading and error states</li>
 <ul>
   
These improvements would optimize performance and user experience while maintaining clean architecture.

## Features
<ul>
  <li>Product listing with pagination</li>
  <li>Product detail view</li>
  <li>Slider for featured products</li>
</ul>

## Tech Stack & Libraries
<ul>
  <li>100% Kotlin</li>
  <li>MVVM Architecture</li>
  <li>Jetpack Components</li>
  <ul>
      <li>ViewModel</li>
      <li>LiveData</li>
      <li>ViewBinding</li>
  </ul>
  <li>Dependency Injection with Hilt</li>
  <li>Retrofit for networking</li>
  <li>Glide for image loading</li>
  <li>Unit Testing with JUnit & Mock</li>
</ul>

## Architecture
The app follows MVVM architecture pattern:
<ul>
  <li>Model: Handles data operations</li>
  <li>View: XML layouts and Activities/Fragments</li>
  <li>ViewModel: Manages UI-related data and business logic</li>
</ul>

## Network Layer
<ul>
  <li>Retrofit for API calls</li>
  <li>Custom NetworkManager for handling responses</li>
  <li>Error handling with Result wrapper</li>
  <li>Pagination implementation for product listing</li>
</ul>

## Testing
<ul>
  <li>Unit tests for Repository layer</li>
  <li>Mock responses for API calls</li>
  <li>Test coverage for business logic</li>
</ul>

## Setup
<ul>
  <li>Clone the repository</li>
  <li>Open project in Android Studio</li>
  <li>Run the app</li>
</ul>


## Requirements
<ul>
  <li>Android Studio Arctic Fox or newer</li>
  <li>Minimum SDK 24</li>
  <li>Target SDK 33</li>
  <li>Kotlin 1.8.0</li>
</ul>


