# android_anim_button
# BlurLockView [![What I've Done](https://github.com/Nightonke/What-I-ve-Done/blob/master/what_i_ve_done.png?raw=true)](https://github.com/Nightonke/What-I-ve-Done)

![BlurLockView](https://github.com/Nightonke/BlurLockView/blob/master/Pictures/in_out.gif)  

Lock view with blur effect. Easy to customise.

# [中文文档](https://github.com/Nightonke/BlurLockView/blob/master/README-ZH.md)

# Note

1. The blur effect comes from [500px-android-blur](https://github.com/500px/500px-android-blur).
2. More animations for showing or hiding the BlurLockView will be added.
3. In the demo, I use [Material-Dialogs](https://github.com/afollestad/material-dialogs) for convenient.

# Usage

[Demo](https://github.com/Nightonke/BlurLockView#demo)  
[Gradle](https://github.com/Nightonke/BlurLockView#gradle)  
[Easy to Use](https://github.com/Nightonke/BlurLockView#easy-to-use)  
[Show and Hide](https://github.com/Nightonke/BlurLockView#show-and-hide)  
[Listeners](https://github.com/Nightonke/BlurLockView#listeners)  
[Blur Effect](https://github.com/Nightonke/BlurLockView#blur-effect)  
[Keyboard](https://github.com/Nightonke/BlurLockView#keyboard)  
[Text](https://github.com/Nightonke/BlurLockView#text)  
[Font](https://github.com/Nightonke/BlurLockView#font)  
[Style](https://github.com/Nightonke/BlurLockView#style)  
[Incorrect Password](https://github.com/Nightonke/BlurLockView#incorrect-password)  

### Demo

Try demo here:  
[Download from Fir](http://fir.im/yakc)  
[Download from Github](https://github.com/Nightonke/BlurLockView/blob/master/Apk/BlurLock-V1.0.0.apk?raw=true)  
![Fir](https://github.com/Nightonke/BlurLockView/blob/master/Pictures/fir.png)  
You can get all about BlurLockView from the demo.  
![Settings](https://github.com/Nightonke/BlurLockView/blob/master/Pictures/test.png)
![Operations](https://github.com/Nightonke/BlurLockView/blob/master/Pictures/operations.png)

### Gradle

Add this to build.gradle:  
```java
dependencies {
    ...
    compile 'com.nightonke:blurlockview:1.0.0'
    ...
}
```

### Easy to Use

Add the xml code:  
```xml
<com.nightonke.blurlockview.BlurLockView
    android:id="@+id/blurlockview"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    />
```
Notice that you should set the BlurLockView to cover the view than need to be blured.

Add this to initialize the BlurLockView:  
```java
// Set the view that need to be blurred
blurLockView.setBlurredView(imageView1);

// Set the password
blurLockView.setCorrectPassword(getIntent().getStringExtra("PASSWORD"));
```

### Show and Hide

You can choose duration, direction and ease type to show or hide the BlurLockView. 
For instance, the gif at the start of readme shows as ```ShowType.FADE_IN``` with 1000ms and ```HideType.FADE_OUT``` with 1000ms. 
You can check all the directions and ease types in the demo above.  

![Ease](https://github.com/Nightonke/BlurLockView/blob/master/Pictures/ease.gif)  

### Listeners

BlurLockView.OnPasswordInputListener  
```java
@Override
public void correct(String inputPassword) {
    // the input password is correct
    // you can hide the BlurLockView, for example
}

@Override
public void incorrect(String inputPassword) {
    // the input password is incorrect
}

@Override
public void input(String inputPassword) {
    // the password is being input
}
```

BlurLockView.OnLeftButtonClickListener  
```java
@Override
public void onClick() {
    // The left button is being clicked
}
```
Implements the listeners above and then:  
```java
blurLockView.setOnLeftButtonClickListener(this);
blurLockView.setOnPasswordInputListener(this);
```
Notice that the right button is set as "Backspace" usually, so there is not OnRightButtonClickListener.  

### Blur Effect

You can set the effect of blur with 3 parameters.  
1. **DownsampleFactor**, with ```setDownsampleFactor(int downsampleFactor)```, the smaller, the clearer.  
2. **BlurRadius**, with ```setBlurRadius(int blurRadius)```, the smaller, the clearer.  
3. **OverlayColor**, with ```setOverlayColor(int color)```, to change the overlay color of BlurLockView.

Examples:  
![clear](https://github.com/Nightonke/BlurLockView/blob/master/Pictures/clear.png)
![unclear](https://github.com/Nightonke/BlurLockView/blob/master/Pictures/unclear.png)

![red](https://github.com/Nightonke/BlurLockView/blob/master/Pictures/red.png)
![blue](https://github.com/Nightonke/BlurLockView/blob/master/Pictures/blue.png)

### Keyboard

You can use different keyboard to get different password.  
```java
setType(Password type, boolean smoothly);
```
Choose ```Password.NUMBER```(default) or ```Password.TEXT``` and whether change password type smoothly. 
Notice that the password with text is case-insensitive(I will improve this).  

![Password Type](https://github.com/Nightonke/BlurLockView/blob/master/Pictures/keyboard.gif)

### Text

1. Set the text of title with ```setTitle(String string)```.
2. Set the text of left button with ```setLeftButton(String string)```.
3. Set the text of right button with ```setRightButton(String string)```.

### Font

You can set all the font of text with ```setTypeface(Typeface typeface)```.

### Style

**1.** Set the background of buttons in Password.TEXT with ```setSmallButtonViewsBackground(int id)```. The default resource drawable is:  
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android" >
    <item android:state_pressed="true" >
        <shape android:shape="oval"  >

        </shape>
    </item>
    <item android:state_focused="true">
        <shape android:shape="oval"  >
            <stroke android:width="1dip" android:color="@color/default_button_press" />
            <solid android:color="@android:color/transparent"/>
        </shape>
    </item>
    <item >
        <shape android:shape="oval"  >
            <stroke android:width="1dip" android:color="@color/default_button_press" />
            <solid android:color="@android:color/transparent"/>
        </shape>
    </item>
</selector>
```

**2.** Set the click effect of buttons in Password.TEXT with ```setBigButtonViewsClickEffect(int id)```. The default resource drawable is:  
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android" >
    <item >
        <shape android:shape="oval"  >
            <solid android:color="@color/default_button_press"/>
        </shape>
    </item>
</selector>
```
**3.** When you click the buttoms in Password.TEXT, the effect above will disappear and you can set the duration by ```setSmallButtonViewsClickEffectDuration(int duration)```.

**4.** Similarly, you can set the 3 styles of buttons in Password.NUMBER like above with ```setBigButtonViewsBackground(int id)```, ```setBigButtonViewsClickEffect(int id)``` and ```setBigButtonViewsClickEffectDuration(int duration)```.

**5.** Try to set the color of all the text with ```setTextColor(int color)```.

**6.** You can get the widgets in BlurLockView by:   
1. ```public TextView getTitle() {return title;}``` to get the title.  
2. ```public TextView getLeftButton() {return leftButton;}``` to get the left button.  
3. ```public TextView getRightButton() {return rightButton;}``` to get the right button.  
4. ```public BigButtonView[] getBigButtonViews() {return bigButtonViews;}``` to get the 10 number buttons in array.  
5. ```public SmallButtonView[][] getSmallButtonViews() {return smallButtonViews;}``` to get all the text buttons in array. Notice that some buttons in the array is null. you can find all the real buttons by this:  
    
```java
private final char CHARS[][] = {
        {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'},
        {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'},
        {   'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'  },
        {        'Z', 'X', 'C', 'V', 'B', 'N', 'M'       }
};
```
    
### Incorrect Password

BlurLockView counts for incorrect input times. You can use ```getIncorrectInputTimes()``` to get the times and use ```setIncorrectInputTimes(int incorrectInputTimes)``` to reset the times.

# Versions
### 1.0.0  

# Todo
1. More animations.  
2. Change the Password.TEXT to case-sensitive with other signals.

# License

    Copyright 2016 Nightonke

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
