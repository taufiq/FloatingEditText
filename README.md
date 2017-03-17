# Floating Edit Text

Sick of Android's Floating Text Input Layout so I decided to create my own

## Getting Started

Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add into dependencies:

```
dependencies {
	        compile 'com.github.taufiqmmhd:FloatingEditText:0.1.5'
	}
```

## Usage
Create it in xml, define your edittextHint and it's ready to go!
```
<com.codigo.floatingedittext.FloatingEditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:editTextHint="Text"
        />
```

## XML Attributes
List of attributes and what they do:

| Attribute  | Purpose | Type |
| ------------- | ------------- | --- |
| editTextHint  | Sets the Hint for the EditText  | String |
| editTextSize  | Sets size of EditText text (in sp)  | float |
| floatingHintSize | Sets size of Floating Hint text (in sp)| float |
| floatingHintAllCaps | Sets if floating Hint text is in caps (false by default)| boolean |
| floatingHintColor | Sets color of floating hint | color |
| editTextBackgroundDrawable | Sets Background of entire FloatingLayout | resourceId |
| editTextTextColor | Set EditText Text Color | color |
| editTextHintColor | Set EditText Hint Color | color |
| editTextFont | Set EditText Font | String |
| floatingHintFont | Set Floating Hint Font | String |
| isPassword | Set input type of EditText to password | boolean |
| floatingHintText | Set Floating Hint text [Uses EditText hint by default] | String |
| isCondenseHint | when true, set visibility of Hint to View.GONE [false by default]| boolean |
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Taufiq Mohammed** - *16 year old*

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
