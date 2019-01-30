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
    					required: "Pole nie może być puste",
    					minlength: "Pole musi zawierać min. 2 znaki",
    					maxlength: "Pole musi zawierać max. 255 znaków"
    				},
    				description: {
    				required: "Pole nie może być puste",
        					minlength: "Pole musi zawierać min. 2 znaki",
        					maxlength: "Pole musi zawierać max. 255 znaków"

			}}
		});
	});