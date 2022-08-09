import Foundation
import UserNotifications


class NotificationCenter: NSObject {
  static let shared = NotificationCenter()

  func authorize(completion: @escaping  (Bool) -> Void) {
    UNUserNotificationCenter.current()
      .requestAuthorization(options: [.alert, .sound, .badge]) { granted, _  in
        completion(granted)
      }
  }

  private func getContent(title: String, body: String) -> UNMutableNotificationContent {

    let content = UNMutableNotificationContent()
    content.title = title
    content.body = body
    content.sound = UNNotificationSound.default
    return content
  }

  private func getTrigger(delay: Double, repeats: Bool) -> UNTimeIntervalNotificationTrigger {

      let trigger = UNTimeIntervalNotificationTrigger(timeInterval: delay, repeats: repeats)

      return trigger
  }

  private func sendNotifier(content: UNMutableNotificationContent, trigger: UNTimeIntervalNotificationTrigger?) -> Void {

    let request = UNNotificationRequest(identifier: UUID().uuidString, content: content, trigger: trigger)
    UNUserNotificationCenter.current().add(request) { error in
      if let error = error {
        print("Error: \(error.localizedDescription)")
      }
    }
  }

func send(notifierObject: NSDictionary) -> Void {

    guard let notifier = notifierObject as? [String: String] else {
        return
    } 
   
    let title = notifier["title"] ?? ""
    let body = notifier["body"] ?? ""
    let content = getContent(title: title, body: body)
        
    sendNotifier(content: content, trigger: nil)
  
}

  func schedule(notifierObject: NSDictionary) -> Void {

    guard let notifier = notifierObject as? [String: Any] else {
        return
    } 
   
    let title = notifier["title"] as? String ?? ""
    let body = notifier["body"] as? String ?? ""
    let content = getContent(title: title, body: body)
    let delay = notifier["delay"] as? Double ?? 0.0
    let trigger = getTrigger(delay: delay, repeats: false)
    
    sendNotifier(content: content, trigger: trigger)
  
}
  
}
