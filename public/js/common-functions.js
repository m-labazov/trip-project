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
		addValueToResult($(this), result);
	});
	row.find('div select').each(function() {
		addValueToResult($(this), result);
	});
	row.find('div textarea').each(function() {
		addValueToResult($(this), result);
	});
	return result;
}

function addValueToResult(row, result) {
	if (row.is(":visible") || row.css('display') !== 'none') {
		result[row.attr('id')] = row.val();
	}
}

function applyObjectToRow(obj, row) {
	for (attr in obj) {
		var element = row.find("#" + attr);
		var value = obj[attr];
		if (element.is('input')) {
			element.val(value);
		} else if (element.is('select')) {
			element.val(value);
		} else if (element.is('textarea')) {
			element.val(value);
		} else if (element.is('div')) {
			element.html(value);
		} else if (element.is('a')) {
			element.attr('href', value);
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