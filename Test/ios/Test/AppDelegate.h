#import <React/RCTBridgeDelegate.h>
#import <UIKit/UIKit.h>
#import <RNLocalNotifierModule.h>

@interface AppDelegate : UIResponder <UIApplicationDelegate, RCTBridgeDelegate, UNUserNotificationCenterDelegate>

@property (nonatomic, strong) UIWindow *window;

@end
