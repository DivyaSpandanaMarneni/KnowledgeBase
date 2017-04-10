<!DOCTYPE html>
<html lang="en">
<head>
  <title>Home Page</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/upload.css">

  <style type="text/css">
    .my_content_container1 a {
      border-bottom: 1px solid #777777;
      border-left: 1px solid #000000;
      border-right: 1px solid #333333;
      border-top: 1px solid #000000;
      color: #000000;
      display: block;
      height: 2.5em;
      padding: 0 1em;
      width: 5em;
      text-decoration: none;
    }

    .my_content_container2 a {
      border-bottom: 1px solid #777777;
      border-left: 1px solid #000000;
      border-right: 1px solid #333333;
      border-top: 1px solid #000000;
      color: #000000;
      display: block;
      height: 2.5em;
      padding: 0 1em;
      width: 12em;
      text-decoration: none;
    }

  </style>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="lib/bootstrap.min.js"></script>
  <script type="text/javascript" src="lib/angular.min.js"></script>
  <script type="text/javascript" src="js/loadFile.js"></script>



</head>
<body style="background-color: #99ccee">
</br>
<div class="media-heading" align="center" style="background-color: #2b669a;height: 100px;align-content: center"><span style="background-color: transparent"><h2 class="media" style="font-family: cursive">Knowledge Cloud</h2></span></div>
<div class="container bg-info">
  <form  enctype="multipart/form-data" style="width: auto;height: auto;align-content: center;">
    <table height="100%" width="100%">
      <tr align="center" style="height: auto">
        <%--<div class="container" align="center"> <td align="center" colspan="2"><h2 class="alert-info" align="center">Knowledge Cloud</h2></td></div>--%>
          <td colspan="2" height="50px"><h4 class="active">Welcome to Knowledge Cloud!</h4></td>
      </tr>

      <tr style="height: auto;">
        <td height="50px">
          <div><button>
            <a href="index.jsp">Home</a></button>
          </div>
        </td>
        <td>
          <%--<a href="UploadRDF.html">RDF Document</a>--%>
          <%--<button onclick="location.href='UploadRDF.html'" id="btnRDF" class="btn-primary">Upload RDF document</button>--%>
          <div><button>
            <a href="UploadRDF.html">Upload RDF Document</a>
          </button></div>
        </td>
      </tr>

    </table>

  </form>
</div>
</body>
</html>