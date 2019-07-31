
//audio control
$(document).ready(function () { 
	var audio;
	var playlist;
	var tracks;
	var current; 
	var playing;
	


	init(); 
	function init() { 
		current = 0;
		audio = $('#audio');
		playlist = $('#playlist');
		tracks = playlist.find('li a');
		len = tracks.length - 1;
		audio[0].volume = .10;
		audio[0].play();
		playlist.find('a').click(function (e) { 
			e.preventDefault();
			link = $(this);
			current = link.parent().index(); 
			run(link, audio[0]); 
			
		}); 
		audio[0].addEventListener('ended', function (e) {
			current++; 
			if (current == len) {
				current = 0; link = playlist.find('a')[0]; 
			} else { 
				link = playlist.find('a')[current];
			}
			run($(link), audio[0]); 
			

			
		});
		

} 
function run(link, player) {
	player.src = link.attr('href'); 
	par = link.parent(); 
	par.addClass('active').siblings().removeClass('active'); 
	audio[0].load(); 
	audio[0].play();
	} 
}) 



//paragraph extension
jQuery(function(){
	var minimized_elements = $('p.minimize');
	minimized_elements.each(function(){
		 var t = $(this).text();        
	        if(t.length < 100) return;
	        
	        $(this).html(
	            t.slice(0,100)+'<span>... </span><a href="#" class="more">More</a>'+
	            '<span style="display:none;">'+ t.slice(100,t.length)+' <a href="#" class="less">Less</a></span>'
	        );
	
	});
	
	  
    $('a.more', minimized_elements).click(function(event){
        event.preventDefault();
        $(this).hide().prev().hide();
        $(this).next().show();        
    });
    
    $('a.less', minimized_elements).click(function(event){
        event.preventDefault();
        $(this).parent().hide().prev().show().prev().show();    
    });
});

//like button 

function updateLike(like, songName)
{
	var xmlhttp;
	like += 1;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  document.getElementById('myDiv').innerHTML = like;
		  document.getElementById('like').style.backgroundColor="blue";
		 
	    }
	  }

	xmlhttp.open("GET","/musiclibrary/playsong?like=" + like, true);
	xmlhttp.send();
}