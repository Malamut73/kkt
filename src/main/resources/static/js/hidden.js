//function start () {
      document.querySelectorAll(".trigger").forEach(el => {
      el.addEventListener("click", () =>{
         let product = el.nextElementSibling;
         
         console.log(product);

         if(product.style.maxHeight){
            product.style.maxHeight = null;
         }else{
            product.style.maxHeight = product.scrollHeight + 'px';
         }

      })
   })
//}
