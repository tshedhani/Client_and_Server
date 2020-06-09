$(document).ready(function () {

	$("#forecast-form").submit(function (event) {

		// stop submit the form, we will post it manually.
		event.preventDefault();
		search_ajax_submit();
	});


	$("#update-form").submit(function (event) {

		// stop submit the form, we will post it manually.
		event.preventDefault();
		update_ajax_submit();
	});


	$("#contracts-form").submit(function (event) {

		// stop submit the form, we will post it manually.
		event.preventDefault();
		contracts_ajax_submit();
	});
});

function employee_ajax_submit() {

	var username = $("#username_employee").val();
	$
		.ajax({

			type: "GET",
			contentType: "application/json",
			url: "/employee/" + username,
			cache: false,
			timeout: 600000,
			success: function (data) {

				$("#username_employee").val("");
				var trHTML = '';
				var item = JSON.parse(data);

				if (item.error != null) {

					$('#result_error').html("<h4>" + item.error + "</h4>");
					document.getElementById('result_error').style.display = 'block';
					document.getElementById('contracts_result').style.display = 'none';
					document.getElementById('error').style.display = 'none';
					alert(item.error);

				} else {

					refreshEmployeeTable();
					document.getElementById('result_error').style.display = 'none';
					document.getElementById('error').style.display = 'none';

					trHTML = "<tr id='fromDB'><td>" + item.sapID + "</td><td>"
						+ item.contractID + "</td><td>" + item.enterpriseID
						+ "</td><td>" + item.employeeName + "</td><td>"
						+ item.level + "</td><td>" + item.assignedCapacity
						+ "</td><td>" + item.rollOnDate + "</td><td>"
						+ item.rollOffDate + "</td><td>" + item.deskAssigned
						+ "</td><td>" + item.laptopID + "</td><td>"
						+ item.laptopSN + "</td><td>" + item.primarySkill
						+ "</td><td>" + item.secondarySkill + "</td><td>" + item.proficiencyLevelPrimary
						+ "</td><td>" + item.proficiencyLevelSecondary + "</td><tr>"

					//addEditButton();

					$('#employee_table').append(trHTML);
					$("#btn-search").prop("disabled", false);
					document.getElementById('employee_result').style.display = 'block';
				}
			},

			error: function (e) {

				$("#username").val("");
				document.getElementById('result_error').style.display = 'none';
				document.getElementById('result').style.display = 'none';
				document.getElementById('error').style.display = 'block';
				alert("An error occured! Check response text in the console logs");
				console.log(e);
			}
		});
}

function contracts_ajax_submit() {

	var username = $("#username_contracts").val();
	$
		.ajax({

			type: "GET",
			contentType: "application/json",
			url: "/contracts/" + username,
			cache: false,
			timeout: 600000,
			success: function (data) {

				$("#username_contracts").val("");
				var trHTML = '';
				var item = JSON.parse(data);

				if (item.error != null) {

					$('#result_error').html("<h4>" + item.error + "</h4>");
					document.getElementById('result_error').style.display = 'block';
					document.getElementById('contracts_result').style.display = 'none';
					document.getElementById('error').style.display = 'none';
					alert(item.error);

				} else {

					refreshContractsTable();
					document.getElementById('result_error').style.display = 'none';
					document.getElementById('error').style.display = 'none';

					trHTML = "<tr id='fromDB'><td>" + item.contractId + "</td><td>"
						+ item.contractName + "</td><td>" + item.cdl
						+ "</td><td>" + item.offshoreMD + "</td><td>"
						+ item.dul + "</td><td>" + item.projectLead
						+ "</td><td>" + item.masterClient + "</td><tr>"

					//addEditButton();

					$('#contacts_table').append(trHTML);
					$("#btn-search").prop("disabled", false);
					document.getElementById('contracts_result').style.display = 'block';
				}
			},

			error: function (e) {

				$("#username").val("");
				document.getElementById('result_error').style.display = 'none';
				document.getElementById('result').style.display = 'none';
				document.getElementById('error').style.display = 'block';
				alert("An error occured! Check response text in the console logs");
				console.log(e);
			}
		});
}

function search_ajax_submit() {

	var username = $("#username").val();
	$("#btn-search").prop("disabled", true);
	$
		.ajax({
			type: "GET",
			contentType: "application/json",
			url: "/forecast/" + username,
			cache: false,
			timeout: 600000,
			success: function (data) {

				$("#username").val("");
				var trHTML = '';
				var item = JSON.parse(data);

				if (item.error != null) {

					$('#result_error').html("<h4>" + item.error + "</h4>");
					document.getElementById('result_error').style.display = 'block';
					document.getElementById('result').style.display = 'none';
					document.getElementById('error').style.display = 'none';
					alert(item.error);

				} else {

					refreshTable();
					document.getElementById('result_error').style.display = 'none';
					document.getElementById('error').style.display = 'none';

					trHTML = "<tr id='titleForeCast'><th>EnterpriseID</th><th>Roll Off Date</th></tr>" +
						"<tr id='forecastDB'><th>" + item.enterpriseID + "</th><th>"
						+ item.rollOffDate + "</th><tr>"

					$('#forecast_table').append(trHTML);

					for (var i = 0; i < item.forecastData.length; i++) {

						addForecastColumnsRow1(item.forecastData[i]);
					}

					for (var i = 0; i < item.forecastData.length; i++) {

						addForecastColumnsRow2(item.forecastData[i]);
					}

					var row = document.getElementById("forecastDB");
					var x = row.insertCell(-1);

					x.innerHTML = '<button id="editButton" onclick="productEdit(this)"' +
						' class="btn btn-primary btn-xs my-xs-btn" type="button">'
						+ '<span class="glyphicon glyphicon-edit"></span> Edit</button>';

					$("#btn-search").prop("disabled", false);
					document.getElementById('forecast').style.display = 'block';
				}
			},

			error: function (e) {

				$("#username").val("");
				document.getElementById('result_error').style.display = 'none';
				document.getElementById('result').style.display = 'none';
				document.getElementById('error').style.display = 'block';
				alert("An error occured! Check response text in the console logs");
				console.log(e);
			}
		});
}

function update_ajax_submit() {

	var formData = JSON.stringify({
		enterpriseId: $("#enterprise").val(),
		date: $("#hours").val(),
		number: parseFloat($("#value").val())
	});

	$.ajax({

		type: "POST",
		contentType: "application/json",
		url: "/updateForecast",
		cache: false,
		timeout: 600000,
		data: formData,
		dataType: "json",
		beforeSend: function (x) {

			if (x && x.overrideMimeType) {
				x.overrideMimeType("application/j-son;charset=UTF-8");
			}
		},

		success: function (data) {
			$('#enterprise').val("");
			$('#hours').val("");
			$('#value').val("");
			$("#username").val("");
			document.getElementById('result').style.display = 'none';

			var item = JSON.parse(JSON.stringify(data));

			if (item.error != null) {
				alert(item.error);
			} else {
				alert("Information Updated Successfully");
			}
		},

		error: function (e) {

			$('#enterprise').val("");
			$('#hours').val("");
			$('#value').val("");
			$("#username").val("");
			document.getElementById('result').style.display = 'none';
			alert("An error occured! Check response text in the console logs");
			console.log(e.responseJSON);
		}
	});
}

function publishForecastData() {

	var myTab = document.getElementById('forecast_table');

	var enterpriseIdFD, rollOffDt;
	var forecastDataFD = [], forecastDataFDNumber = [], forecastDataFDDates = [];
	var counterNumber = 0, counterDate = 0;

	for (i = 0; i < myTab.rows.length; i++) {

		var objCells = myTab.rows.item(i).cells;

		for (var j = 0; j < objCells.length; j++) {

			if (isNumber(objCells.item(j).innerHTML)) {

				if (i > 0 && j > 1) {

					forecastDataFDNumber[counterNumber] = objCells.item(j).innerHTML;
					counterNumber++;
				} else {

					if (i < 1 && j > 1) {
						forecastDataFDDates[counterDate] = objCells.item(j).innerHTML;
						counterDate++;
					}
				}

			} else {

				if (j < 1 && i > 0) {
					enterpriseIdFD = objCells.item(j).innerHTML;
					rollOffDt = objCells.item(j + 1).innerHTML;
				}
			}
		}
	}

	for (var forecastData = 0; forecastData < forecastDataFDDates.length; forecastData++) {

		forecastDataFD.push({
			date: forecastDataFDDates[forecastData],
			number: forecastDataFDNumber[forecastData]
		});
	}

	var formForecastData = JSON.stringify({

		enterpriseID: enterpriseIdFD,
		rollOffDate: rollOffDt,
		forecastData: forecastDataFD
	});

	$.ajax({

		type: "POST",
		contentType: "application/json",
		url: "/postForecast",
		cache: false,
		timeout: 600000,
		data: formForecastData,
		dataType: "json",

		beforeSend: function (x) {

			if (x && x.overrideMimeType) {
				x.overrideMimeType("application/j-son;charset=UTF-8");
			}
		},

		success: function (data) {

			$('#enterprise').val("");
			$('#hours').val("");
			$('#value').val("");
			$("#username").val("");
			document.getElementById('result').style.display = 'none';

			var item = JSON.parse(JSON.stringify(data));

			if (item.error != null) {
				alert(item.error);
			} else {
				alert("Information Updated Successfully");
			}
		},

		error: function (e) {

			$('#enterprise').val("");
			$('#hours').val("");
			$('#value').val("");
			$("#username").val("");
			document.getElementById('result').style.display = 'none';
			alert("An error occured! Check response text in the console logs");
			console.log(e.responseJSON);
		}
	});
}

function refreshContractsTable() {

	var elmtTable = document.getElementById('contacts_table');
	var tableRows = elmtTable.getElementsByTagName('tr');
	var rowCount = tableRows.length;

	for (var x = rowCount - 1; x >= 1; x--) {

		document.getElementById("contacts_table").deleteRow(x);
	}
}

function refreshTable() {

	var elmtTable = document.getElementById('forecast_table');
	var tableRows = elmtTable.getElementsByTagName('tr');
	var rowCount = tableRows.length;

	if (rowCount > 1) {

		for (var x = rowCount - 1; x > -1; x--) {
			document.getElementById("forecast_table").deleteRow(x);
		}
	}
}

function removeForecastClearButton() {
	//document.getElementById('clearForeCast').setAttribute("style", "visibility:hidden");
	document.getElementById('clearForeCast').remove();
	refreshTable();
}

function refreshEmployeeTable() {

	var elmtTable = document.getElementById('employee_table');
	var tableRows = elmtTable.getElementsByTagName('tr');
	var rowCount = tableRows.length;

	for (var x = rowCount - 1; x >= 1; x--) {

		document.getElementById("employee_table").deleteRow(x);
	}
}

function addForecastColumnsRow1(forecast) {

	var row1 = document.getElementById("titleForeCast");
	var x1 = row1.insertCell(-1);
	x1.innerHTML = forecast.date;
}

function addForecastColumnsRow2(forecast) {

	var row2 = document.getElementById("forecastDB");
	var x2 = row2.insertCell(-1);
	x2.innerHTML = forecast.number;
}

function productEdit(item) {

	var currentTD = $(item).parents('tr');

	$.each(currentTD, function () {
		$(this).prop('contenteditable', true)
	});

	alert("Forecast row is editable now");
	document.getElementById("editButton").disabled = true;
	var row = document.getElementById("forecastDB");
	var x = row.insertCell(-1);
	x.innerHTML = '<button onclick="publishForecastData()" class="btn btn-primary btn-xs my-xs-btn" type="button">'
		+ '<span class="glyphicon glyphicon-update"></span> Update</button>';
}

function isNumber(n) {

	return !isNaN(parseFloat(n)) && !isNaN(n - 0)
}