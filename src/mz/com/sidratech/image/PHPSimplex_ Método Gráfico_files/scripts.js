function float2fraction(x){
	var tolerance = 1.e-13;
	var h1 = 1;
	var h2 = 0;
	var k1 = 0;
	var k2 = 1;
	var neg = 1;
	if((x < tolerance)&&(x > -tolerance)){
		return 0;
	}
	else{
		if(x < 0){
			neg = -1;
			x = neg*x;
		}
		var b = x;
		do{
			a = Math.floor(b);
			var aux = h1;
			h1 = a*h1+h2;
			h2 = aux;
			aux = k1;
			k1 = a*k1+k2;
			k2 = aux;
			b = 1/(b-a);
		}while(Math.abs(x-h1/k1) > x*tolerance);
		if(neg == -1){
			h1 = neg*h1;
		}
		if(k1 == 1){
			return h1;
		}
		else{
			return h1+" / "+k1;
		}
	}
}
function Ventana(url,titulo,ancho,alto,barra){
	var especificaciones="top=200,left=300,toolbar=no,location=no,status=no,menubar=no,scrollbars="+barra+",resizable=no,width="+ancho+",height="+alto;
	window.open(url,titulo,especificaciones);
}
function favoritos(idioma){
	if ((navigator.appName=="Microsoft Internet Explorer") && (parseInt(navigator.appVersion)>=4)){
		if (idioma == "es"){var url="http://www.phpsimplex.com/";}
		else if (idioma == "en"){var url="http://www.phpsimplex.com/en";}
		else if (idioma == "fr"){var url="http://www.phpsimplex.com/fr";}
		else if (idioma == "pt"){var url="http://www.phpsimplex.com/pt";}
		var titulo="PHPSimplex";
		window.external.AddFavorite(url,titulo);
	}
	else{
		if (navigator.appName == "Netscape"){
			if (idioma == "es"){
				alert("Presione Ctrl+D para agregar este sitio a sus Bookmarks");}
			else if (idioma == "en"){alert("Press Ctrl+D to Bookmark this page");}
			else if (idioma == "fr"){alert("Pressez Ctrl+D pour ajouter cette page aux Bookmarks");}
			else if (idioma == "pt"){alert("Pressione Ctrl+D para adicionar este site aos seus Bookmarks");}
			else{alert("Press Ctrl+D to Bookmark this page");}
		}
	}
}
function CreateAsyncProcess(url_process){
	var AsyncProcess = document.createElement('script');
	AsyncProcess.type = 'text/javascript';
	AsyncProcess.async = true;
	AsyncProcess.src = url_process;
	document.getElementsByTagName('head')[0].appendChild(AsyncProcess);
}
