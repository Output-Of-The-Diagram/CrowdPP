중간 시연 영상(5/30): https://youtu.be/w5wRyqIjkOA

최종 시연 영상(6/19): https://youtu.be/DFMd2Q--fL4

팀원: 김민제, 박세호, 최영락, 최지훈

사용 방법

1. 깃허브에서 모든 파일을 다운로드하여 받아옵니다.
2. SQL에서"CrowdplusplusScriptFile.sql"을 오픈하여 실행합니다.<br>
(mySQL에서 에러 발생 시 위 스크립트 파일의 3번 줄의 주석을 풀고 패스워드를 입력해주세요)<br>
3. server 폴더의 main.js에서 password에 본인의 패스워드를 입력해주세요.
4. 이후 터미널에서 server 디렉토리로 이동 후 "npm i"를 실행한 뒤 "npm run server"를 실행합니다.
5. 안드로이드 스튜디오에서 가상 Device를 실행한 뒤 어플을 실행하면 끝입니다.


![image](https://github.com/Output-Of-The-Diagram/CrowdPP/assets/55120784/6425dcbc-4f6b-49d9-821d-8a26039baa36)

# 기능적인 요구 사항

## 1. Use case Diagram

![스크린샷 2023-06-18 오전 11.17.51.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-06-18_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%258C%25E1%2585%25A5%25E1%2586%25AB_11.17.51.png)

## 2. Use Case Description

![스크린샷 2023-04-04 오후 10.55.14.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-04_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_10.55.14.png)

![스크린샷 2023-04-04 오후 10.55.32.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-04_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_10.55.32.png)

![스크린샷 2023-04-04 오후 10.55.56.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-04_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_10.55.56.png)

![스크린샷 2023-04-04 오후 11.25.41.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-04_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_11.25.41.png)

![스크린샷 2023-04-04 오후 10.56.39.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-04_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_10.56.39.png)

![스크린샷 2023-04-04 오후 10.56.51.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-04_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_10.56.51.png)

![스크린샷 2023-04-04 오후 10.57.02.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-04_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_10.57.02.png)

![스크린샷 2023-04-04 오후 10.57.13.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-04_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_10.57.13.png)

![스크린샷 2023-04-04 오후 10.57.24.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-04_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_10.57.24.png)

# 비기능적인 요구 사항

# Domain Model

| Class 명 | Concept 설명 | 주요 attributes |
| --- | --- | --- |
| SignUpManager | 회원가입을 처리하는 Controller | id, password, userData |
| LoginManager | 로그인을 처리하는 Controller | id, password |
| RequestCrowdManager | 모임 신청을 담당하는 Controller | userData, crowdData |
| ReceiveCrowdManager | 모임 신청들을 관리하는 Controller | userData, crowdData, date |
| WithdrawalManager | 모임 탈퇴를 관리하는 Controller | userData, crowdData |
| ExileManager | 모임 추방을 관리하는 Controller | userData, crowdData |
| CheckMemberManager | 회원 확인을 하는 Controller | Id, isValid, userData |
| CreateCrowdManager | 새로운 모임을 등록하기 위한 Controller | userData, crowdData |
| RemoveCrowdManager | 모임을 삭제하기 위한 Controller | userData, crowdData |
|  |  |  |
| UserDatabase | 유저의 개인정보들 | userDataDict |
| CrowdDatabase | Crowd들의 정보 | crowdDataDict, userDataDict |
| RequestDatabase | 모임 신청들의 정보 | userDataDict |
|  |  |  |
| SignUp | 유저에게 회원가입에 필요한 정보를 받아 SignUpManager에게 전달함 | id, password, userData |
| Login | 유저가 로그인 버튼을 클릭할 때 받은 id, password 정보를 LoginManager에게 전달함 | id, password |
| RequestCrowd | 유저가 가입 버튼을 클릭할 때 유저와 Crowd 정보를 RequestCorwdManager에 전달함 | userData , crowdData |
| ReceiveCrowd | 유저가 모임 가입을 승낙 또는 거절할 때 그 정보를 ReceiveCrowdManager에 전달함 | userData, crowdData |
| CreateCrowd | 유저가 모임 생성에 필요한 정보를 입력하여 CreateCrowdManager에게 전달함 | userData, crowdData |
| RemoveCrowd | 유저가 모임 삭제를 위해 필요한 정보를 입력하여 RemoveCrowdManager에게 전달함 | userData, crowdData |
|  |  |  |
| ShowWindowInterface | 화면을 보여주는 Interface |  |
| ShowWindowSignUp | SignUpManager에서 필요한 화면 출력 | id, password, userData |
| 위와 마찬가지로 모든 상황 별 화면 출력 |  |  |

# Interaction Diagram

## Use Cases

### 1. 회원가입

1. System Sequence Diagram
    
    ![스크린샷 2023-05-02 오후 11.17.22.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-05-02_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_11.17.22.png)
    
2. Operation Contract
    
    
    | Name | RequestCreationAccount |
    | --- | --- |
    | Responsibility | 회원가입에 필요한 정보들을 입력하여 회원가입을 요청한다. 이후 회원가입 성공 여부에대한 결과값을 돌려받는다.  |
    | Pre-conditions | Form에 id와 pw를 입력해야한다. |
    | Post-conditions | 회원가입의 성공여부를 돌려받는다. |
    
    | Name | RequestDeleteAccount |
    | --- | --- |
    | Responsibility | 회원탈퇴를 위한 요청을 보낸다. |
    | Pre-conditions | 계정이 등록되어있고, 로그인 상태이어야한다. |
    | Post-conditions | 회원탈퇴 결과에 대한 결과값을 반환받는다. |
3. Sequence Diagram
    
    ![스크린샷 2023-05-02 오후 11.55.28.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-05-02_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_11.55.28.png)
    

### 2. 로그인

1. System Sequence Diagram
    
    ![스크린샷 2023-05-02 오후 11.17.59.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-05-02_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_11.17.59.png)
    
2. Operation Contract
    
    
    | Name | RequestLogin(id, pw) |
    | --- | --- |
    | Responsibility | 입력 받은 id, pw값으로 로그인을 요청한다. |
    | Pre-conditions | 계정이 등록되어있는 상태이어야한다. |
    | Post-conditions | 로그인 성공 여부에 대한 결과값을 받는다. |
    
    | Name | RequestLogout |
    | --- | --- |
    | Responsibility | 로그아웃을 위한 요청을 보낸다. |
    | Pre-conditions | 로그인이 되어있는 상태이어야한다. |
    | Post-conditions | 로그아웃에 대한 결과값을 받는다. |
    1. Sequence Diagram
        
        ![스크린샷 2023-05-02 오후 11.54.37.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-05-02_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_11.54.37.png)
        

### 3. 모임 신청

1. System Sequence Diagram
    
    ![스크린샷 2023-05-02 오후 11.42.31.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-05-02_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_11.42.31.png)
    
2. Operation Contract
    
    
    | Name | request(userID, crowdID) |
    | --- | --- |
    | Responsibility | request의 정보를 system에 보낸다 |
    | Pre-condition | 유저가 모임 가입을 신청하려 한다 |
    | Post-condition | 가입 요청을 DB에 저장한다. |
    
3. Sequence Diagram
    
    ![스크린샷 2023-05-02 오후 11.39.44.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-05-02_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_11.39.44.png)
    

### 4. 모임 신청 받기

1. System Sequence Diagram
    
    ![스크린샷 2023-05-02 오후 11.42.26.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-05-02_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_11.42.26.png)
    
2. Operation Contract
    
    
    | Name | checkRequest(userID, crowdID) |
    | --- | --- |
    | Responsibility | 본인이 모임장인 crowd의 request의 정보를 받아온다 |
    | Pre-condition | 모임장이 로그인 되어있다 |
    | Post-condition | crowd의 request 정보를 받아온다. |
    
    | Name | accept(userID, crowdID) |
    | --- | --- |
    | Responsibility | 모임장이 request를 수락한다. |
    | Pre-condition | 모임장이 로그인 되어있다. |
    | Post-condition | 신청자가 모임에 가입된다. |
3. Sequence Diagram
    
    ![스크린샷 2023-05-02 오후 11.40.04.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-05-02_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_11.40.04.png)
    

### 5. 모임 등록

1. System Sequence Diagram

![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled.png)

1. Operation Contract

| Name | RequestCreateCrowd(userId, crowdName) |
| --- | --- |
| Responsibility | 유저가 Crowd의 이름을 작성하고 Create Crowd 버튼을 누른다. 시스템은 Crowd의 이름이 중복되는지 검사한다. 중복되지 않는다면 CrowdDatabase에 Crowd를 추가하고 그 결과를 유저에게 알려준다 |
| Pre-conditions | 유저가 Crowd를 생성한다. |
| Post-conditions | Crowd가 생성되고 해당 Crowd를 보여준다 |
1. sequence diagram

![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled%201.png)

### 6. 모임 삭제

1. System Sequence Diagram

![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled%202.png)

1. Operation Contract

| Name | RequestRemoveCrowd |
| --- | --- |
| Responsibility | 유저(리더)가 Crowd를 삭제하기 위해 Remove Crowd 버튼을 누른다. 시스템은 버튼을 누른 사람이 해당 모임의 리더인지를 확인하고 CrowdDatabase에서 Crowd를 삭제한다. |
| Pre-conditions | 리더가 Crowd를 삭제한다. |
| Post-conditions | Crowd가 삭제된 결과를 유저에게 보여주고 메인 화면으로 이동한다.ㅔ⁰ |
1. Sequence Diagram

![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled%203.png)

### 7. 캘린더

1. System Sequence Diagram 

    
    ![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled%204.png)
    
2. Operation Contracts
    
    
    | Name | ClickButtonMemo |
    | --- | --- |
    | Responsibility | 메모버튼을 눌러서 메모창을 활성화 시킨다. |
    | Pre-conditions | 로그인이 되어있어야한다. |
    | Post-conditions | 메모를 작성할 수 있는 창이 열린다. |
    
    | Name | WriteMemo(date, title, memo) |
    | --- | --- |
    | Responsibility | 일정을 작성한다. |
    | Pre-conditions | 일정 작성 From에서 date, title, memo 등 필요한 것들을 작성한다. |
    | Post-conditions | 작성한 일정이 등록되어 캘린더에 반영된다. |
3. System Diagram
    
    ![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled%205.png)
    

### 8. 약속 잡기

1. System Sequence Diagram

    
    ![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled%206.png)
    
2. Operation Contracts

| Name | ClickButtonPromise() |
| --- | --- |
| Responsibility | 약속 생성 버튼을 누른다. |
| Pre-conditions | 유저가 약속을 생성한다. |
| Post-conditions | 약속 일정과 내용을 적는 창이 출력된다. |

| Name | WritePromise(date, promise) |
| --- | --- |
| Responsibility | 약속 일정과 내용을 작성한다. |
| Pre-conditions | 유저가 약속 생성 버튼을 눌러 ClickButtonPromise()가 실행된다. |
| Post-conditions | 작성된 일정과 내용을 PromiseManager에게 전달하여 PromiseDatabase에 저장될 수 있도록 한다. |

| Name | ClickButtonJoin() |
| --- | --- |
| Responsibility | 약속 참가 버튼을 누른다 |
| Pre-conditions | 유저가 약속에 참가하기위해 버튼을 누른다. |
| Post-conditions | 약속 참가 숫자가 올라서 참여를 나타나게함. |
1. Sequence Diagram

![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled%207.png)

# Class Diagram

class diagram version 1

![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled%208.png)

class diagram version 2

![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled%209.png)

# 중간 시연 영상 링크: [https://youtu.be/w5wRyqIjkOA](https://youtu.be/w5wRyqIjkOA)

# 최종 시연 영상 링크: [https://youtu.be/DFMd2Q--fL4](https://youtu.be/DFMd2Q--fL4)

# Github 링크: [https://github.com/Output-Of-The-Diagram/CrowdPP](https://github.com/Output-Of-The-Diagram/CrowdPP)

1. GRASP을 표시한 Class Diagram

![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled%2010.png)

1. SW Design Pattern 을 적용한 Class Diagram

![Untitled](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/Untitled%2011.png)

# Design Refinement

| Before design classifier | After design classifier | 적용한 설계 개념 | 합리성 | NFR, QA 분석 |
| --- | --- | --- | --- | --- |
| NULL | AcceptRefuseMemberAdapter | Adapter Pattern,
Indirection | 멤버 추방을 구현하기 위한 어댑터.
서버로부터 받아온 JSON 멤버 데이터를 뷰로 변환하여 화면에 띄워주는 역할 | Adapter를 사용함으로써 Reusability, Flexibility, Scalability, Maintainability, Testability, Portability가 향상되었다.  |
| NULL | ApplyRefuseAdapter | Adapter Pattern,
Indirection | 모임 신청 수락 및 거절을 위한 어댑터.
서버로부터 받아온 신청내역 JSON 데이터를 뷰로 변환하여 화면에 띄워주는 역할 | Adapter를 사용함으로써 Reusability, Flexibility, Scalability, Maintainability, Testability, Portability가 향상되었다.  |
| NULL | MembersAdapter | Adapter Pattern,
Indirection | 각 모임의 멤버목록을 조회하기 위한 어댑터. 서버로부터 받아온 멤버 정보 JSON 데이터를 뷰로 변환하여 화면에 띄워주는 역할 | Adapter를 사용함으로써 Reusability, Flexibility, Scalability, Maintainability, Testability, Portability가 향상되었다.  |
| NULL | RecyclerAdapter | Adapter Pattern,
Indirection | 모든 모임의 목록을 조회하기 위한 어댑터. 
서버로부터 받아온 모든 모임의 JSON 데이터를 뷰로 변환하여 화면에 띄워주는 역할 | Adapter를 사용함으로써 Reusability, Flexibility, Scalability, Maintainability, Testability, Portability가 향상되었다.  |
| NULL | RetrofitInterface | Facade Pattern,
Indirection | 미리 파라미터와 받을 데이터의 종류 및 개수 등 규약을 정해놓으면, 사용할 때 내부의 코드를 이해하지 않고 바로 요청을 할 수 있다. | Interface를 사용함으로써
Modularity, Flexibility, Testability, Reusability, Scalability,  Interoperability가 향상되었다. |
| NULL | RetrofitInterface | Observer Pattern | Retrofit 통신의 비동기 요청 처리에 대한 응답을 관찰하고 처리. onResponse, onFailure 메서드가 관찰자로 작용 | Interface를 사용함으로써
Modularity, Flexibility, Testability, Reusability, Scalability,  Interoperability가 향상되었다. |
| NULL | RetrofitInterface | Low Coupling | 서브 시스템의 내부 구조를 알 필요 없이 Interface만 알고 있으면 된다. 그 결과 클라이언트와 서브시스템의 결합도를 낮춘다. | Interface를 사용함으로써 Reusability, Modifiability   가 향상되었다. |
| NULL | RetrofitInterface | High Cohesion | 서브 시스템의 각종 기능들을 하나의 단일 interface로 묶었다. 그 결과 서브 시스템은 논리적인 응집된 단위로 구성이 되었고, 각종 기능들이 캡슐화 되어있다. | Interface를 사용함으로써 Reusability,  Modifiability, Maintainability,  가 향상되었다. |

GoF: Adapter, Factory, Singleton, Stratgey, Composite, Facade, Observer

GRASP:****Creator,Controller,[Low Coupling](https://en.wikipedia.org/wiki/Loose_coupling),[High Cohesion](https://en.wikipedia.org/wiki/Cohesion_(computer_science)),[Polymorphism](https://en.wikipedia.org/wiki/Polymorphism_(computer_science)),Pure Fabrication,Indirection,Protected Variations,Information Expert**

# Snapshot

### 설계

![스크린샷 2023-06-18 오후 12.40.09.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-06-18_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_12.40.09.png)

### DB 스키마

![스크린샷 2023-05-23 오후 5.40.27.png](HW5-Final%20Project%207a747dda55ab43688cd24b07d82204e0/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-05-23_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_5.40.27.png)

### 구현 결과

- [https://youtu.be/DFMd2Q--fL4](https://youtu.be/DFMd2Q--fL4) 최종 시연 영상 링크

### 미구현 기능 및 기타 기능

- Crowd 내에서 Calender를 통한 약속 및 일정 잡기, 과제 부여, 랜덤 뽑기 등 다양한 기능들을 구현하고 싶었으나 다른 기능들을 구현하는 데 많은 어려움을 겪고 시간이 오래 걸려 이들을 구현하지 못하였다.
- Crowd를 생성할 때 이미지를 업로드하고 이를 대표 이미지로 설정하고 싶었지만 이미지를 DB에 저장하고 불러오는 데 시간이 너무 오래 걸려 제거하였습니다.

### 소감

이 과목을 통해 객체지향적인 설계를 배울 수 있었습니다. 초기에는 객체지향의 개념과 원칙에 대해 이론적인 이해만을 가지고 있었는데, 과제를 만들면서 팀원들과 협업하며 실제로 객체지향 패턴들을 적용하여 개발을해보니 확실히 더 명확하게 깨닫게 되었습니다. 요구사항 분석, 설계 단계에서의 모델링, 클래스 및 인터페이스 설계, 패턴 적용 등 다양한 측면에서의 경험을 쌓을 수 있었습니다. 또한 팀원들과의 협업을 통해 서로의 아이디어를 나누고 토의하며, 문제를 해결하는 과정에서 많은 것을 배웠습니다. 

이번 기회에 자바기반의 앱을 개발해보고, 서버를 개발해봄으로서 RSETfufl에 대한 개념도 알게되었습니다. 앞으로 배운 내용을 다른 프로그래밍 언어나 개발 패러다임에도 적용해볼 것입니다.
