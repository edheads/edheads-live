class Header extends HTMLElement {
    constructor() {
        super();
    }
    connectedCallback() {
        this.innerHTML = `

  <style>
    
.stickyHeader{
    position: fixed;
    z-index: 2;
}

header {
    background-color: #2cccd3;
    background-image: url(/assets/headerClouds.png);
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    border-bottom: .5rem solid #cbd543;
    justify-content: space-around;
    position: relative;
    width: 100vw;
}

.headerContents{
    width: 90%;
    margin: 0 auto;
}

.logo{
    position: absolute;
    z-index: 1;
}

.logoImg{
    width:20vw;
}

.mainNavContainer{
    align-items: flex-end;
    display: flex;
    justify-content: center;
}


.mainNav{
    display: flex;
    list-style-type: none;
}

.mainNavItem{
    background-color: #cbd543;
    border-top-left-radius: .75rem;
    border-top-right-radius: .75rem;
    margin-left: .75rem;
    padding: .75rem 2rem;
    text-transform: uppercase;
    white-space: nowrap;
}

.mainNavItem a{
    color: #474747;
    text-decoration: none;
}

.mainNavItem a:hover{
    font-weight: 900;
}

.mainNavItem.active a{
    font-weight: 900;
}

.active:after{
    display: none;
}

.topNav{
    display: flex;
    justify-content: end;
}

.topNavItem{
    background-color: #cbd543;
    border-bottom-left-radius: .75rem;
    border-bottom-right-radius: .75rem;
    font-size: 1rem;
    margin-left: .75rem;
    padding: .5rem 1rem;
    text-transform: uppercase;
    white-space: nowrap;
}

.topNavItem a{
    color: #474747;
    text-decoration: none;
}

.topNavItem a:hover{
    font-weight: 900;
}

.logoWG{
    transform: translate(-2vw, 1vw);
}

.logoImgWG{
    width:15vw;
}
  </style>
  <div class="stickyHeader">
    <header>
        <div class="headerContents">
            <div class="topNav">
                <ul class="mainNav">
                    <li class="topNavItem"><a href="/login">Log In</a></li>
                    <li class="topNavItem"><a href="/sign-up">Sign Up</a></li>
                </ul>
            </div>
            <div class="logo">
                <img src="/assets/ed_logo.png" alt="Edheads logo" class="logoImg">
            </div>

<!--            <div class=""> </div>-->

            <div class="mainNavContainer">
            
                <ul class="mainNav">
                    <li sec:authorize="isAuthenticated()" class="mainNavItem"><a href="/teachers">Teachers</a></li>
                    <li class="mainNavItem active" style="margin-left: 1rem;"><a href="/welcome">Welcome</a></li>
                    <li class="mainNavItem"><a href="/about-us">About Us</a></li>
                    <li class="mainNavItem"><a href="/our-games">Our Games</a></li>
                    <li class="mainNavItem"><a href="/get-involved">Get Involved</a></li>
                    <li class="mainNavItem"><a href="/donate">Donate</a></li>
                </ul>
     
          <div class="logoWG">
                    <img src="/assets/weatherhead.PNG" alt="Edheads Weathergirl Mascot" class="logoImgWG">
                </div>
            </div>
        </div>
    </header>
`;
  }
}
customElements.define('header-component', Header);