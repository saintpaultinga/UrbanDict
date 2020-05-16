# UrbanDict
I created this app for interview purpose

# Description
the app allows a user to search for a word definition. It uses the urban-dictionary API

# Usage

launch the app and enter the search term in the search view and click your soft keyboar search button
or click the submit button on the right side of the search view.

# How the resutls are displayed

* The default sort:  order the definitions according to they relative index (increasing order)
* Thumbs up: order the definitions based on the number of thumbs up they have (decreasing order: more thumbs up comes first)
* Thumbs down: order the definition base on the number of thumbs down they have (increasing order, less thumbs down comes first)

# Caching
I used okhttp to cache the result. When a term is term is searched and the app is offline, the result of an old search of the term
will be returned.

# Testing
I've added one instrumented test for the `SearchActivity` and more unit tests to cover the presenter, the repository, the adapter and the viewHolder.

# Architecture
The whole app is based on mosby MVP frameword architecture. Mosby viewstate is used to maintain the activity state when
configuration changes. This avoid making some unecessary network call.
