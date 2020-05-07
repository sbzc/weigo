/* -------------------------------------------------------------------------------- /
	
	Magentech jQuery
	Created by Magentech
	v1.0 - 20.9.2016
	All rights reserved.
	
/ -------------------------------------------------------------------------------- */


	// Cart add remove functions
	var cart = {
		'add': function(product_id) {
			var searchQuery = $("#searchQuery").val();
			var searchPage = $("#searchPage").val();
			var quantity=$("#itemNum").val();
			$.ajax({
				url:'/checkLogin',
				type:'post',
				success:function(data){
					if(data.code==1){
						$.ajax({
							url:'http://47.103.218.192:8084/cart/add/'+product_id+'.action?num='+quantity,
							type:'post',
							dataType:'jsonp',
							success:function(data){
								addProductNotice('成功加入购物车', '<img src="'+data.msg+'" alt="加载失败">', '<h3>三天内有效,请尽快处理哦</h3>', 'success');
							}
						});
					}else{
						location.href="/search.html?page="+searchPage+"&q="+searchQuery;
					}
				}
			});
			
					
		}
	
	}

	var wishlist = {
		'add': function(product_id) {
			addProductNotice('Product added to Wishlist', '<img src="image/catalog/demo/product/travel/1.jpg" alt="">', '<h3>You must <a href="#">login</a>  to save <a href="#">Apple Cinema 30"</a> to your <a href="#">wish list</a>!</h3>', 'success');
		}
	}
	var compare = {
		'add': function(product_id) {
			addProductNotice('Product added to compare', '<img src="image/catalog/demo/product/travel/1.jpg" alt="">', '<h3>Success: You have added <a href="#">Apple Cinema 30"</a> to your <a href="#">product comparison</a>!</h3>', 'success');
		}
	}

	/* ---------------------------------------------------
		jGrowl – jQuery alerts and message box
	-------------------------------------------------- */
	function addProductNotice(title, thumb, text, type) {
		$.jGrowl.defaults.closer = false;
		//Stop jGrowl
		//$.jGrowl.defaults.sticky = true;
		var tpl = thumb + '<h3>'+text+'</h3>';
		$.jGrowl(tpl, {		
			life: 4000,
			header: title,
			speed: 'slow',
			theme: type
		});
	}

