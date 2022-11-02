var a=0;
function show()
{
    if(a==1){
    	a=0;
        document.getElementById("form").style.display="none";
    }
    else{
    	a=1;
        document.getElementById("form").style.display="block";
    }
}
