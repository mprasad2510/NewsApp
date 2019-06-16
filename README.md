# NewsApp
# Android-Mvi
MVI Architecture with Kotlin , Rxjava . Testing with Mockito,Espresso

## Library Used
* RxJava2 and RxAndroid
* Retrofit / OkHttp
* Gson
* Dagger 2
* Timber

## Tests
* run `./gradlew test` for unit tests.
* UI tests 
* Jacoco for Code Coverage 
 No coverage: No instruction in the line has been executed (red background)
 Partial coverage: Only a part of the instruction in the line have been executed (yellow background)
 Full coverage: All instructions in the line have been executed (green background)
 you will find the .txt file in this destination
        xml
                {
                    destination file("${buildDir}/reports/jacoco/test/jacocoTestReport.xml")
                }
        html
                {
                    destination file("${buildDir}/reports/jacoco/test/")
                }
   To run the test with code coverage , you need to click on Java->Run 'All Tests' with coverage , it will generate the analysis report


## TODO
* Add Database Support (Wip on offline_support branch)

## License

```
MIT License

Copyright (c) [2018] [Manisha Prasad]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

