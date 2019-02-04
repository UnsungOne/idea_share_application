	$(document).ready(function() {
		$("#addIdeaForm").validate({
			rules: {
				title: {
					required: true,
					minlength: 2,
					maxlength: 255
				},

				description: {
                    required: true,
                    minlength: 2,
                    maxlength: 255
				},

				},
	        messages: {
    				title: {
    					required: "Pole nie może być puste.",
    					minlength: "Pole musi zawierać min. 2 znaki.",
    					maxlength: "Pole musi zawierać max. 255 znaków."
    				},
    				description: {
    				required: "Pole nie może być puste.",
        					minlength: "Pole musi zawierać min. 2 znaki.",
        					maxlength: "Pole musi zawierać max. 255 znaków."

			}}
		});

		$("#addUserForm").validate({
			rules: {
				name: {
					required: true,
					minlength: 2,
					maxlength: 255
				},

				email: {
                    required: true,
                    minlength: 2,
                    maxlength: 255
				},

                password: {
                    required: true

                },
                repeatPassword: {
                    required: true

                },
				},
	        messages: {
    				name: {
    					required: "Pole nie może być puste.",
    					minlength: "Pole musi zawierać min. 2 znaki.",
    					maxlength: "Pole musi zawierać max. 255 znaków."
    				},
    				email: {
    				required: "Pole nie może być puste.",
        					minlength: "Pole musi zawierać min. 2 znaki.",
        					maxlength: "Pole musi zawierać max. 255 znaków."
                    },

                    password: {
                    required: "Pole nie może być puste."
                    },

                      repeatPassword: {
                required: "Pole nie może być puste."

			}}
		});

	});