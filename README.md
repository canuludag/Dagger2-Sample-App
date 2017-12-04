Dagger2-Sample-App
--------------------
This application is a sample for using [Dagger 2][1] Dependency Injection Library. Main goal is to demonstrate how to use Dagger 2 in a real application scenario. For this purpose I used NewsApi.org API and created a small news listing application. The API key that used in this project was deprecated and you have to get your own API key from NewsApi.org in order to fetch news articles.

Libraries Used In This Project
------------------------------------
Alongside the Dagger 2 framework, I also used several frameworks such as ButterKnife for view binding and Retrofit for networking operations. All can be found in gradle files. Here is the list:
* [Dagger][1]
* [ButterKnife][2]
* [Retrofit][3]
* [Glide][4]

Dagger Modules Created For This Project
-----------------------------------
* **NewsApplicationModule**
    * Provides Application Context
* **SharedPrefsModule**
    * Provides SharedPreferences and Prefs Editor
* **NewsApiModule**
    * Provides Retrofit api service

Developed By
---------------
* Can Uludağ  - https://www.linkedin.com/in/canuludag/
 
License
----------
    Copyright 2017 Can Uludağ

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: https://github.com/google/dagger
[2]: https://github.com/JakeWharton/butterknife
[3]: http://square.github.io/retrofit/
[4]: https://github.com/bumptech/glide