function getResponseBody(data) {
	if (data.statusCode === 'OK') {
		return data.data;
	} else {
		alert("The error was handled. 'Make good window'");
	}
}

function createObjectFromRow(row) {
	var result = {};
	row.find('div :input').each(function() {
		result[$(this).attr('id')] = $(this).val();
	});
	row.find('div select').each(function() {
		result[$(this).attr('id')] = $(this).val();
	});
	row.find('div textarea').each(function() {
		result[$(this).attr('id')] = $(this).val();
	});
	return result;
}

function applyObjectToRow(obj, row) {
	for (attr in obj) {
		var element = row.find("#" + attr);
		if (element.is('input')) {
			element.val(obj[attr]);
		} else if (element.is('select')) {
			element.val(obj[attr]);
		} else if (element.is('textarea')) {
			element.val(obj[attr]);
		} else if (element.is('div')) {
			element.html(obj[attr]);
		}
	}
}

function clearForm(form) {
	form.find('div :input').each(function() {
		$(this).val("");
	});
	form.find('div select').each(function() {
		$(this).val("");
	});
	form.find('div textarea').each(function() {
		$(this).val("");
	});
}