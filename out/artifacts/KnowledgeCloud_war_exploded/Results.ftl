<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Display Results</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/upload.css">
    <link rel="stylesheet" href="css/jqcloud.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="lib/bootstrap.min.js"></script>
    <script type="text/javascript" src="lib/angular.min.js"></script>
    <script type="text/javascript" src="js/loadFile.js"></script>
    <script type="text/javascript" src="js/service.js"></script>
    <script type="text/javascript" src="js/directive.js"></script>
    <script type="text/javascript" src="js/jqcloud-1.0.4.js"></script>

    <script>
        var keyWordsSt = "${keyWords}";
        var word_array = keyWordsSt.split(" ");

        var word_array = [
            {text: "abc", weight: 15},
            {text: "def", weight: 9},
            {text: "ghi", weight: 6},
            {text: "jkl", weight: 7},
            {text: "mno", weight: 5}
            // ...as many words as you want
        ];


        alert("keywords" +word_array);
        console.log("keywords"+word_array);

        $(function () {
           $('#example').jQCloud(word_array);
        });
    </script>


</head>
<body style="background-color: #99ccee">
</br>
<div class="media-heading" align="center" style="background-color: #2b669a;height: 100px;align-content: center"><span style="background-color: transparent"><h2 class="media" style="font-family: cursive">Knowledge Cloud</h2></span></div>
<div class="container bg-info">
    <table>
        <tr><td><label name="fileName" id="file"><tt>${fileName}</tt></label> </td>
            <td><label name="keyWords" id="keyWords">Key words:</label></td>
            <#--<td><div id="wordCloud" class="" style="width: 550px; height: 350px; position: relative;"><span class="col-lg-offset-4"><tt id="keyWordsArr"></tt></span></div> </td>-->
            <td><div id="example" style="width: 550px; height: 350px;"></div> </td>
        </tr>

        <tr><td><input type="text" name="txtTagWord"/></td></tr>
        <tr><td><button type="submit" ng-click="submitTagWord()" class="btn-group-lg btn-lg btn-primary">Submit</button> </td></tr>
    </table>
</div>

</body>
</html>