#GestureFun [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-GestureFun-green.svg?style=true)](https://android-arsenal.com/details/1/3084) [![API](https://img.shields.io/badge/API-10%2B-brightgreen.svg?style=flat-square)](https://android-arsenal.com/api?level=10) [![license](https://img.shields.io/badge/license-apache 2.0-lightgrey.svg?style=flat-square)](http://www.apache.org/licenses/LICENSE-2.0.html)
A library with `custom views` based on `gestures`.

# Demo

 ![Demo](demo.gif)

_Note: Sample app is also included in the repo so you can check the code_


#  Usage:

Add `jitpack.io` in your root `build.gradle` :
```groovy
allprojects {
 repositories {
    maven { url "https://jitpack.io" }
 }
}
```
_Note:_ do not add the `jitpack.io` repository under `buildscript`

Add the `dependency` in your app `build.gradle` :
```groovy
dependencies {
    compile 'com.github.hrskrs:GestureFun:1.2'
}
```

### DeleteOnSwipeEditText:
You can delete whole text by swiping(with single or two fingers) from the left to the right or vise versa. 

In layout:

``` xml

    <com.hrskrs.gesturefunlibrary.DeleteOnSwipeEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        custom:hrskrs_swipe_mode="2"
        custom:hrskrs_threshold="200" />
```

Attributes:
``` xml
<!-- Swipe threshold default value: 200 -->
<!-- If threshold is smaller than default value, threshold is set to default one -->
<!-- If threshold exceeds the width of View itself, threshold is set to View width - 100px(DEFAULT_PADDING) -->
<attr name="hrskrs_threshold" format="integer" /> 
<!--Swipe mode default value: 2 -->
<!-- To swipe with single finger set hrskrs_swipe_mode to 1 -->
<!-- To swipe with double finger set hrskrs_swipe_mode to 2 -->
<attr name="hrskrs_swipe_mode" format="integer" />
```

Attributes (Programatically):

```java
//Setting-Getting swipe mode dynamically
deleteOnSwipeEditText.setSwipeMode(int);
deleteOnSwipeEditText.getSwipeMode();
//Setting-Getting threshold dynamically
deleteOnSwipeEditText.setThreshold(int);
deleteOnSwipeEditText.getThreshold();
```
	
# License

    Copyright (C) 2016 Haris Krasniqi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
