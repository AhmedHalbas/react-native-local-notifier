//
//  RNLocalNotifierModule.m
//  RNLocalNotifierModule
//
//  Copyright © 2022 Ahmed Essam. All rights reserved.
//

#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RNLocalNotifierModule, NSObject)
RCT_EXTERN_METHOD(requestAuthorization: (RCTPromiseResolveBlock)resolve rejecter: (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(sendNotification: (NSDictionary *)notifierObject)
RCT_EXTERN_METHOD(scheduleNotification: (NSDictionary *)notifierObject)

@end
