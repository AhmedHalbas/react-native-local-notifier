import { NativeModules, Platform } from 'react-native'

type sendNotifierObject = {
  title: String
  body: String
}

type scheduleNotifierObject = {
  title: String
  body: String
  delay: Number
}

const NativeModule = NativeModules.RNLocalNotifierModule

const LocalNotifier = {
  requestAuthorization: (): Promise<boolean> => {
    if (Platform.OS === 'ios') return NativeModule.requestAuthorization()
    else return Promise.resolve(true)
  },

  sendNotification: (notifierObject: sendNotifierObject) =>
    NativeModule.sendNotification(notifierObject),

  scheduleNotification: (notifierObject: scheduleNotifierObject) =>
    NativeModule.scheduleNotification(notifierObject),
}

export default LocalNotifier
