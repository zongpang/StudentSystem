jQuery(document)
		.ready(
				function() {

					$('.page-container form').submit(
							function() {
								var userName = $(this).find('.username').val();
								var password = $(this).find('.password').val();
								var userId = $(this).find('.user').val();
								if (userName == '') {
									$(this).find('.error').fadeOut('fast',
											function() {
												$(this).css('top', '27px');
											});
									$(this).find('.error').fadeIn(
											'fast',
											function() {
												$(this).parent().find(
														'.username').focus();
											});
									return false;
								}

								if (userId == '') {
									$(this).find('.error').fadeOut('fast',
											function() {
												$(this).css('top', '165px');
											});
									$(this).find('.error').fadeIn(
											'fast',
											function() {
												$(this).parent().find('.user')
														.focus();
											});
									return false;
								}

								if (userId == '教师' || userId == '班主任'
										|| userId == '管理员' || userId == '院长') {
									return true;
								} else {
									$(this).find('.error').fadeOut('fast',
											function() {
												$(this).css('top', '165px');
											});
									$(this).find('.error').fadeIn(
											'fast',
											function() {
												$(this).parent().find('.user')
														.focus();
											});
									return false;
								}

							});

					$(
							'.page-container form .username, .page-container form .password, .page-container form .user,')
							.keyup(
									function() {
										$(this).parent().find('.error')
												.fadeOut('fast');
									});

				});
