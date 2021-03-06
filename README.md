[![CircleCI](https://circleci.com/gh/kanujgit/NewsApp/tree/main.svg?style=svg)](https://circleci.com/gh/kanujgit/NewsApp/tree/main) 
[![codebeat badge](https://codebeat.co/badges/2876f2a1-ab51-40c4-8685-e82790811257)](https://codebeat.co/projects/github-com-kanujgit-newsapp-main)
# News App  

News App is a simple news app which uses [NewsAPI](https://newsapi.org/) to fetch top news headlines from the API. 

### Project Overview

The goal is to create a News app which gives a user updated news from the internet 
related to a particular category.

The codebase focuses on following key things:
- Code structuring, style and comments
- Offline
- Kotlin + Coroutines
- room database
- coroutine flow
- Retrofit


## API key
You'll need to provide API key to fetch the news from the News Service (API). Currently the news is fetched from [NewsAPI](https://newsapi.org/)

- Generate an API key (It's only 2 from [NewsAPI](https://newsapi.org/)
- Create new file named -> `credentials.properties` in our project root folder
- Add the API key as shown below [Make sure to keep the double quotes]:
```
    NEWS_API_KEY = "<INSERT_YOUR_API_KEY>"
    API_BASE_URL = "<INSERT_YOUR_BASE_URL>"
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
* Room database


## Architecture

The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)


