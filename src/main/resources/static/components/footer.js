class Footer extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        this.innerHTML = `
      <style>
footer {
    background-color: #474747;
    display: flex;
    justify-content: space-around;
    width: 100vw;
}

.footerText{
    color: #fff;
    font-size:1rem;
    padding: 3rem;
    width: 40%;
}

.footerLinks{
    display: flex;
    padding: 3rem;
}

.socialIcon{
    margin-right: .5rem;
    max-height: 50px;
}

.footerButton{
    background-color: #cbd543;
    border: none;
    border-radius: .5rem;
    font-size: 1rem;
    font-weight: 900;
    letter-spacing: .05rem;
    max-height: 50px;
    padding: 1rem 1.5rem;
    text-transform: capitalize;
    white-space: nowrap;
}

.footerButton a{
    color: #ffffff;
    text-decoration: none;
}
      </style>
      <footer>
  <div class="footerText">
    <p>Edheads is a non-profit that creates unique, free games 
    that tie K12 math and science curricula to STEM careers.
    </p>
  </div>

  <div class="footerLinks">
    <a href="https://www.facebook.com/Edheads" target="_blank">
      <img src="/assets/facebook.png" alt="Link to Edheads on Facebook" class="socialIcon"></a>
    <a href="https://twitter.com/Edheadsdotorg" target="_blank">
      <img src="/assets/twitter.png" alt="Link to Edheads on Twitter" class="socialIcon"></a>
    <a href="https://www.linkedin.com/company/edheads" target="_blank">
      <img src="/assets/instagram.png" alt="Link to Edheads on Instagram" class="socialIcon"></a>
    <a href="https://www.pinterest.com/edheads/_created/" target="_blank">
      <img src="/assets/pinterest.png" alt="Link to Edheads on Pinterest" class="socialIcon"></a>
    <a href="https://www.youtube.com/user/Edheadsorg" target="_blank">
      <img src="/assets/youTube.png" alt="Link to Edheads on YouTube" class="socialIcon"></a>
    <button class="footerButton"><a href="mailto:info@edheads.org">Contact Us!</a></button>
  </div>

</footer>
    `;
    }
}

customElements.define('footer-component', Footer);