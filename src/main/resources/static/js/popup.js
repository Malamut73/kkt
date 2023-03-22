const modals = () =>{
    function bindModal(trigger, modal, close) {
        trigger.addEventListener('click', (e) => {
            if(e.target){
                e.preventDefault();
            }

            modal.style.display = "block";
            document.body.style.overflow = "hidden";
        });

        close.addEventListener('click', () => {
            modal.style.display = "none";
            document.body.style.overflow = "";
        });

        modal.addEventListener('click' ,(e) => {
            if(e.target === modal){
            modal.style.display = "none";
            document.body.style.overflow = "";
            }
        });
    }

    const callPopupBtn = document.querySelector('.logo__inner'),
        modalPopup = document.querySelector('.popup_test'),
        modalPopupClose = document.querySelector('.popup_close');

    bindModal(callPopupBtn, modalPopup, modalPopupClose);

}

modals();



