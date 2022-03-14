# SearchBooks

### Description.
도서 검색에 대한 결과물을 리스트 형식으로 보여주고, 도서를 클릭하게 되면 해당 도서에 대한 디테일한 정보를 제공.
a "-" b로 검색을 하게 되면, a 키워드에 대한 검색 결과물에서 b 키워드가 들어간 검색 결과물을 제외한 뒤 노출.
a "|" b로 검색을 하게 되면, a 키워드에 대한 검색 결과와 b 키워드에 대한 검색 결과물을 합쳐서 노출.

##

#### UI. 
* RecyclerView
* ListAdatper
* Fragment
* Activity

<img width="765" alt="image" src="https://user-images.githubusercontent.com/58131221/158194425-c60224dc-7e8b-4b2b-88a9-8c76b4d0bb3a.png">

##

#### 구조 및 라이브러리.
* MVVM
* aac
* Coroutine
* Retrofit

##

#### 패키지 및 데이터 구성.
* `adapter package`
  - 도서 검색에 대한 처리를 위한 **List Adapter**
    * **List Adapter 사용 목적**
      - RecyclerView.Adapter를 베이스로 한 클래스로 RecyclerView의 List 데이터를 표현하고, 백그라운드 스레드에서 차이(diff)를 처리.
      - **DiffUtil**
        * 현재 데이터 리스트와 교체될 데이터 리스트를 비교하여 무엇이 바꼈는지 알아내서 RecyclerView의 기존 아이템에 수정 또는 변경이 있을 시 변경되야 하는 데이터만 빠르게 바꿔줌.

* `model package`
  - 도서 검색과 해당 도서의 디테일 정보를 위한 Api 요청 시 응답 데이터를 담는 클래스
    * **Data Class 사용 목적**
      - Data Class의 생성자로 SerializedName을 사용하여, 응답 데이터를 파싱하게 하면 Reflection을 통해 프로퍼티에 데이터가 파싱됨에 따라, 파싱 과정에서의 에러를 줄이는 동시에 결과 값을 객체로 반환할 수 있는 장점이 있다.
  
    
  - 응답 데이터를 리스트에 보여주기 위해, UI에 맞게 가공하는 클래스
    * **UI 가공 클래스 사용 목적**
      - 파싱된 응답데이터는 ViewModel에서 View에 보여질 데이터를 미리 가공하여 전달하게 되면 View에서는 그대로 노출처리만 하면 되기 때문에 가독성이 높아지고 코드가 간결해질 수 있음.
  
* `network package`
  - 네트워크 요청 방식 및 응답 데이터 형태를 정의하는 인터페이스

* `repository package`
  - Retrofit 객체와 네트워크 요청 시, 응답에 대한 인터페이스 구현체를 담는 클래스
  - 네트워크 요청 및 응답에 대한 인터페이스를 주입 받은 클래스
  
* `source package`
  - 도서 정보 리스트 요청과 특정 도서의 정보를 위한 인터페이스 
  
* `view package`
  - 도서 정보 리스트와 도서의 디테일한 정보를 보여주는 프래그먼트
  
* `viewmodel package`
  - 도서 정보 리스트와 도서의 디테일한 정보를 담는 프래그먼트에서 처리하는 비즈니스 로직을 담는 뷰 모델 클래스
    * **ViewModel 사용목적**
      - View에서는 UI적인 처리만 하고, View가 참조하는 ViewModel에서는 구독한 View에게 전달하는 모든 데이터 처리를 하여, LiveData를 통해 View에게 전달한다. 이렇게 되면 View와 ViewModel은 각각의 역할만 함에 따라 높은 가독성, 간결성, 유지보수성에 대한 장점이 있다.

##  

