<p>
  <a href="https://www.npmjs.com/package/react-native-local-notifier" target="_blank">
    <img alt="Version" src="https://img.shields.io/npm/v/react-native-local-notifier.svg">
  </a>
  <a href="#" target="_blank">
    <img alt="License: MIT" src="https://img.shields.io/badge/License-MIT-yellow.svg" />
  </a>
</p>

# React Native Local Notifications for iOS and Android

## Install

```sh
npm i react-native-local-notifier
```

## Linking

### React Native v0.60+

The package is [automatically linked](https://github.com/react-native-community/cli/blob/master/docs/autolinking.md) when building the app. All you need to do is:

For ios:

```bash
cd ios && pod install
```

For android, the package will be linked automatically on build.

**NOTE: If you target iOS you will still have to manually update the AppDelegate.h and AppDelegate.mm (as below).**

**NOTE: For Android, you will still have to manually update the AndroidManifest.xml (as below).**

## Android Configurations

**NOTE: sendLocalNotification() works without changes in the application part, while scheduleLocalNotification() only works with these changes:**

In your `android/app/src/main/AndroidManifest.xml`

```xml

   <manifest xmlns:android="http://schemas.android.com/apk/res/android"
  package="com.test">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM"/> <!-- add this permission -->

    <application
      android:name=".MainApplication"
      android:label="@string/app_name"
      android:icon="@mipmap/ic_launcher"
      android:roundIcon="@mipmap/ic_launcher_round"
      android:allowBackup="false"
      android:theme="@style/AppTheme">

      <receiver
            android:name="com.ahmedessam.helpers.NotificationReceiver"
            android:enabled="true"/> <!-- add this receiver -->

      ...
    </application>
</manifest>

```

## iOS Configurations

### Update `AppDelegate.h`

At the top of the file:

```objective-c
#import <RNLocalNotifierModule.h>
```

Then, add the 'UNUserNotificationCenterDelegate' to protocols:

```objective-c
@interface AppDelegate : UIResponder <UIApplicationDelegate, RCTBridgeDelegate, UNUserNotificationCenterDelegate>

```

### Update `AppDelegate.mm`

at the end of your AppDelegate file before @end, add the following:

```objective-c
- (void)registerForLocalNotifications {

   UNUserNotificationCenter *center = [UNUserNotificationCenter currentNotificationCenter];
   center.delegate = self;
}

-(void)userNotificationCenter:(UNUserNotificationCenter *)center willPresentNotification:(UNNotification *)notification withCompletionHandler:(void (^)(UNNotificationPresentationOptions options))completionHandler{

   UIApplicationState state = [[UIApplication sharedApplication] applicationState];

  if (state == UIApplicationStateActive) {
      if (@available(iOS 14.0, *)) {
        completionHandler(UNNotificationPresentationOptionBanner);
      } else {
        completionHandler(UNNotificationPresentationOptionAlert);

      }

    }

    else {

      if (@available(iOS 14.0, *)) {
        completionHandler(UNNotificationPresentationOptionSound | UNNotificationPresentationOptionBanner);
      } else {
        completionHandler(UNNotificationPresentationOptionSound | UNNotificationPresentationOptionAlert);
      }
    }

}
```

And then add the following line in didFinishLaunchingWithOptions:

```objective-c
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  ...
  [self registerForLocalNotifications]; // add this line
  ...

  return YES;
}
```

## Import Package

```js
import LocalNotifier from 'react-native-local-notifier'
```

## Grant Permission (iOS Only)

```js
LocalNotifier.requestAuthorization() : Promise<boolean>
```

EXAMPLE:

```javascript
useEffect(() => {
  LocalNotifier.requestAuthorization().then((isGranted) => {
    console.log('requestAuthorization', isGranted)
  })
}, [])
```

## Local Notifications

```js
LocalNotifier.sendNotification(details: Object)
```

EXAMPLE:

```javascript
const notifierObject = {
  title: 'Some Title',
  body: 'this is some body',
}
LocalNotifier.sendNotification(notifierObject)
```

## Scheduled Notifications

```js
LocalNotifier.scheduleNotification(details: Object)
```

EXAMPLE:

```javascript
const notifierObject = {
  title: 'Some Scheduled Title',
  body: 'this is some Scheduled body',
  delay: 5, // in seconds
}
LocalNotifier.scheduleNotification(notifierObject)
```

## Author

👤 **Ahmed Halbas**

- Github: [@AhmedHalbas](https://github.com/AhmedHalbas)
- LinkedIn: [@AhmedHalbas](https://linkedin.com/in/AhmedHalbas)

## 🤝 Contributing

Contributions, issues and feature requests are welcome!<br />Feel free to check [issues page](https://github.com/AhmedHalbas/react-native-local-notifier/issues).

## Show your support

Give a ⭐️ if this project helped you!
