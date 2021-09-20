
# AudioRecorder / 오디오 녹음기
1. 뷰 구성하기
2. 권한 요청하기
3. 녹음 기능 구현하기

> State
녹음 전 -> 녹음 중 -> 녹음 후 -> 재생 중<br>

### CustomView
- #### 안드로이드 공식 문서
- - #### Creating a custom view class - https://developer.android.com/training/custom-views/create-view
<br>

### RECORDE_AUDIO Permission
- 오디오 권한은 위험 권한이므로 안드로이드 6.0 이상부터는 반드시 사용자 권한(런타임 시)을 받아야 한다.
- #### 안드로이드 공식 문서
- - #### MediaRecorder overview - https://developer.android.com/guide/topics/media/mediarecorder
- - #### Request app permissions - https://developer.android.com/training/permissions/requesting
<br>

### AppCompat
-  기존 클래스를 랩핑하여 이전 버전에서도 새로 출시한 기능들을 정상적으로 사용 가능하게 해주는 라이브러리

### MediaRecorder
- #### 안드로이드 공식 문서
- - #### MediaRecorder - https://developer.android.com/reference/android/media/MediaRecorder