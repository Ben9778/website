/*作者：黄滨*/
$(function() {

	/* 判断内容隐藏删除按钮 */
	$('.new-body-item').length > 0 ? $("#del").css("display", "inline") : $(
			"#del").css("display", "none")
	/* 无内容显示文字提醒 */
	$('.new-body-item').length == 0 ? $(".no-new").css("display", "block") : $(
			".no-new").css("display", "none")
	/** ***添加新闻***** */
	$("#insert").click(function() {
		clearText()
		$(".save").hide()
		$(".publish").show()
		$("#insert-form,.shadow").show()
		$(".insertwarn").css("display", "none")
	})
	function clearText() {
		$(".title,.introduce,.dates,.imgPath,.new_id").val("")
		tinyMCE.activeEditor.setContent("")
		$(".picture").attr("src",
				"http://www.fjtydy.com/picture/data/add1.png")
	}
	// 发布
	$(".publish").click(function() {
		let insertUrl = "http://www.fjtydy.com/insertNew"
		addServer(insertUrl)
	})

	/** ***编辑新闻***** */
	$(".edit").click(function() {
		var a = $(this).parents(".new-body-item")
		$(".new_id").val(a.find("input[type=checkbox]").val())
		$(".title").val(a.find("h4").html())
		$(".introduce").val(a.find("p").eq(1).html())
		$(".dates").val(a.find("p").eq(0).html())
		$(".picture").attr("src", a.children("img")[0].src)
		$(".imgPath").val("")
		tinyMCE.activeEditor.setContent(a.find("textarea").val())
		$(".save").show()
		$(".publish").hide()
		$(".insertwarn").css("display", "none")
		$("#insert-form,.shadow").show()
	})
	// 保存
	$(".save").click(
			function() {
				var updateUrl = "http://www.fjtydy.com/updateNew"
				var setUrl = "http://www.fjtydy.com/setNew"
				var datas = new FormData()
				tinyMCE.activeEditor.save()
				var new_id = $(".new_id").val()
				var title = $(".title").val()
				var introduce = $(".introduce").val()
				var dates = $(".dates").val()
				var content = $(".content").val()
				var imgPath = $(".imgPath")[0].files[0]
				if (imgPath != null) {
					if (title != "" && introduce != "" && dates != ""
							&& content != "") {
						datas.append("new_id", new_id)
						datas.append("title", title)
						datas.append("introduce", introduce)
						datas.append("dates", dates)
						datas.append("content", content)
						datas.append("imgPath", imgPath)
						$.ajax({
							type : "POST",
							url : updateUrl,
							data : datas,
							dataType : "json",
							processData : false,
							contentType : false,
							success : function(msg) {
								if (msg.code == "yes") {
									alert("修改成功!")
									location.reload()
									$(".shadow,#insert-form").hide()
								} else {
									alert("修改失败!")
								}
							}
						})
					} else {
						$(".insertwarn").show()
					}
				} else {

					if (title != "" && introduce != "" && dates != ""
							&& content != "") {
						$.ajax({
							type : "POST",
							url : setUrl,
							data : {
								"new_id" : new_id,
								"title" : title,
								"introduce" : introduce,
								"dates" : dates,
								"content" : content
							},
							dataType : "json",
							success : function(msg) {
								if (msg.code == "yes") {
									alert("修改成功!")
									location.reload()
									$(".shadow,#insert-form").hide()
								} else {
									alert("修改失败!")
								}
							}
						})
					} else {
						$(".insertwarn").show()
					}
				}

			})

	function addServer(url) {
		tinyMCE.activeEditor.save()
		var datas = new FormData()
		var title = $(".title").val()
		var introduce = $(".introduce").val()
		var dates = $(".dates").val()
		var imgPath = $(".imgPath")[0].files[0]
		var content = $(".content").val()

		if (title != "" && introduce != "" && dates != "" && content != ""
				&& imgPath != null) {
			datas.append("title", title)
			datas.append("introduce", introduce)
			datas.append("dates", dates)
			datas.append("content", content)
			datas.append("imgPath", $(".imgPath")[0].files[0])
			$.ajax({
				type : "POST",
				url : url,
				data : datas,
				dataType : "json",
				processData : false,
				contentType : false,
				success : function(msg) {
					if (msg.code == "yes") {
						alert("添加成功!")
						location.reload()
						$(".shadow,#insert-form").hide()
					} else {
						alert("添加失败!")
					}
				}
			})
		} else {
			$(".insertwarn").show()
		}
	}

	$(".cancels").click(function() {
		$("#insert-form,.shadow").hide()
	})
	$(".title,.introduce,.dates").focus(function() {
		$(".insertwarn").css("display", "none")
	})
	$(".picture").click(function() {
		$(".imgPath").click()
	})
	$(".imgPath").change(function() {
		$(".picture").attr("src", URL.createObjectURL($(this)[0].files[0]))
	})

	/** ***删除新闻***** */

	// 删除选中的id值
	$("#del").click(function() {
		var num = $(".checks:checked").length
		if (num == 0) {
			alert("请选择数据!")
		} else {
			var ids = []
			$(".checks:checked").each(function() {
				ids.push($(this).val())
			})
			if (confirm("是否删除？")) {
				$.ajax({
					type : "get",
					url : "http://www.fjtydy.com/deleteNew",
					dataType : "json",
					data : {
						"new_id" : ids
					},
					traditional : "true",
					success : function(msg) {
						if (msg.code == "yes") {
							alert("删除成功！")
							location.reload()
						} else {
							alert("删除失败！")
						}
					}
				})
			}
		}

	})
})
