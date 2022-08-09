import React, { useEffect } from 'react'
import { Button, View } from 'react-native'
import LocalNotifier from 'react-native-local-notifier'

const App = () => {

  useEffect(() => {
    LocalNotifier.requestAuthorization().then((isGranted) => {
      console.log('requestAuthorization', isGranted)
    })
  }, [])

  const scheduleLocalNotification = () => {
    const notifierObject = {
      title: 'Some Scheduled Title',
      body: 'this is some Scheduled body',
      delay: 5 // in seconds
    }
    LocalNotifier.scheduleNotification(notifierObject)
  }

  const sendLocalNotification = () => {
    const notifierObject = {
      title: 'Some Title',
      body: 'this is some body',
    }
    LocalNotifier.sendNotification(notifierObject)
  }
  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'space-evenly' }}>

      <Button title='Notify Me Now' onPress={sendLocalNotification} />

      <Button title='Notify Me After 5 Seconds' onPress={scheduleLocalNotification} />

    </View>
  )
}

export default App