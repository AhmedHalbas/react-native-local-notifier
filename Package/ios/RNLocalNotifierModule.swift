import Foundation

@objc(RNLocalNotifierModule)
class RNLocalNotifierModule: NSObject {

  @objc
  static func requiresMainQueueSetup() -> Bool {
    return true
  }

   @objc
  func requestAuthorization(_ resolve: @escaping RCTPromiseResolveBlock, rejecter reject: @escaping RCTPromiseRejectBlock ) -> Void {
    
              NotificationCenter.shared.authorize { granted in
                resolve(granted)
              }
  
  }

  @objc
  func sendNotification(_ notifierObject: NSDictionary) -> Void {

    NotificationCenter.shared.send(notifierObject: notifierObject)
  }

   @objc
  func scheduleNotification(_ notifierObject: NSDictionary) -> Void {

    NotificationCenter.shared.schedule(notifierObject: notifierObject)
  }

}
