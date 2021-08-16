# News App

News App is a simple news app which uses [NewsAPI](https://newsapi.org/) to fetch top news headlines from the API. 

### Project Overview

The goal is to create a News app which gives a user updated news from the internet 
related to a particular category.

The codebase focuses on following key things:
- Code structuring, style and comments
- Offline
- Kotlin + Coroutines


## API key
You'll need to provide API key to fetch the news from the News Service (API). Currently the news is fetched from [NewsAPI](https://newsapi.org/)

- Generate an API key (It's only 2 from [NewsAPI](https://newsapi.org/)
- Create new file named -> `credentials.properties` in our project root folder
- Add the API key as shown below [Make sure to keep the double quotes]:
```
    NEWS_API_KEY = "<INSERT_YOUR_API_KEY>"
```


### Features

* Navigation Drawer
* Fragments
* ViewPager plus TabLayout
* Intent
* [NewsAPI](https://newsapi.org/)
* JSON Parsing
* Coil
* CardView
* RecyclerView
* Coroutine


## Architecture

The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)


