

# PushAlarm / 푸시 알람 수신기
### Firebase Cloud

#### FCM 메시지

1) 알림 메시지
2) 데이터 메시지



#### Android 메시지 처리

- 모든 메시지는 수신된 지 20초 (Android mashmallow의 경우 10초) 이내에 처리되어야 함.

**알림 메시지**

- 앱이 백그라운드에 있을 때 onMessageReceived를 호출하지 않고, 알아서 화면에 보여줌
- 포그라운드에 있을 때 onMessageReceived 메서드 호출

**데이터 메시지**

- 포그라운드, 백그라운드 상태 모두 onMessageReceived 호출



#### 등록 토큰 액세스

**토큰 변경**

- 새 기기에서 앱 복원
- 사용자가 앱 삭제/재설치
- 사용자가 앱 데이터 소거

> 라이브 서비스의 경우, 토큰이 갱신될 때마다 서버에 해당 토큰을 갱신하는 작업을 해줘야 함. (onNewToken() 메서드 활용)



**[Firebase 공식 문서] - 테스트 메시지 전송**

https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages/send



**[Android 공식 문서] - 알림 채널 만들기 및 관리**

- Android 8.0(API 수준 26) 부터는 모든 알림을 채널에 할당해야 함.
- 보내야 하는 고유한 유형의 알림마다 채널을 생성해야 함.

> Android 8.0을 타겟팅하는 앱이 알림 채널을 지정하지 않고 알림을 게시하려고 하면 **토스트 메시지**로 화면 경고를 표시하는 설정을 할 수 있음
>
> **설정 > 개발자 옵션 > 알림 채널 경고 표시**

https://developer.android.com/training/notify-user/channels?hl=ko

