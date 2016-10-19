# ARootPermissionsDetector
Simple utility class combining several methods for checking root permissions on android devices

## How do I get set up? ##
Get it via Gradle
```groovy
compile 'it.xabaras.android:arootpermissionsdetector:0.1'
```
or Maven
```xml
<dependency>
  <groupId>it.xabaras.android</groupId>
  <artifactId>arootpermissionsdetector</artifactId>
  <version>0.1</version>
  <type>pom</type>
</dependency>
```

Or download the [latest AAR](https://bintray.com/xabaras/maven/ARootPermissionsDetector/_latestVersion) and add it to your project's libraries.

## Usage ##
Just import it.xabaras.android.arootpermissionsdetector.ARootPermissionsDetector and call

```java
ARootPermissionsDetector.isRooted(getApplicationContext())
```