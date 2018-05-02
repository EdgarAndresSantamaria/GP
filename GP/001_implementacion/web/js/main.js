document.getElementById("botonDescarga").addEventListener("click",function(){
  document.getElementById("botonDescarga").style.display = "none";
  document.getElementById("anuncio1").removeAttribute("style");
  document.getElementById("anuncio2").removeAttribute("style");

  // similar behavior as clicking on a link
  //  window.location.href = "http://stackoverflow.com";
});
