# ARootPermissionsDetector
Simple utility class combining several methods for checking root permissions on android devices

## How do I get set up? ##
Get it via Gradle
```groovy
compile 'it.xabaras.android:arootpermissionsdetector:1.0'
```
or Maven
```xml
<dependency>
  <groupId>it.xabaras.android</groupId>
  <artifactId>arootpermissionsdetector</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```

Or download the latest AAR and add it to your project's libraries.

[ ![Download](https://api.bintray.com/packages/xabaras/maven/ARootPermissionsDetector/images/download.svg) ](https://bintray.com/xabaras/maven/ARootPermissionsDetector/_latestVersion)

## Usage ##
Just import it.xabaras.android.arootpermissionsdetector.ARootPermissionsDetector and call

```java
ARootPermissionsDetector.isRooted(getApplicationContext())
```
