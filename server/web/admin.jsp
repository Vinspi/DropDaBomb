<%@ page import="View.AdminPackView" %><%--
  Created by IntelliJ IDEA.
  User: deutsch
  Date: 09/03/17
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="materialize/css/materialize.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"  media="screen,projection"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="materialize/js/materialize.js"></script>


    <meta charset="utf-8">
    <title>DropDaBomb</title>
</head>
<body class="center-align">

<%
    AdminPackView apv = new AdminPackView();


%>

<h3>Panneau d'administration des packs</h3>
    <!-- Pack -->
    <div class="container pack">
        <div class="row">
            <div class="col s12 m12 l12">
                <!-- LootPack -->
                <div class="container lootpack">
                    <div class="row">
                        <div class="col s12 m12 l12">

                            <!-- Ensemble -->
                            <div class="container ensemblepack">
                                <div class="row">
                                    <div class="col s12 m12 l12">
                                        <h5>Gestion des ensembles</h5>
                                            <div class="card">
                                                <div class="card-content white-text">
                                                    <p>Gestion des Ensembles</p>
                                                    <div id="listEnsembles" class="row">
                                                        <%
                                                            for(int i = 0; i < apv.getListEnsembles().size();i++){

                                                            }
                                                        %>
                                                    </div>
                                                    <div id="btnEnsemble"><a class="waves-effect waves-light btn">Add</a><a class="waves-effect waves-light btn">Remove</a></div>
                                                    <p>Ensemble courant</p>
                                                    <div id="currentEnsemble" class="row">

                                                    </div>
                                                </div>
                                                <div class="card-action">
                                                    <a class="waves-effect waves-teal btn-flat" onclick=newEnsemble()>New</a>
                                                    <a class="waves-effect waves-teal btn-flat" onclick=createEnsemble()>Create</a>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                            <h5>Gestion des ensembles</h5>
                                <div class="card">
                                    <div class="card-content white-text">
                                        <p>Gestion des Ensembles</p>
                                        <div id="listLP" class="row">

                                        </div>
                                        <div id="btnLP"><a class="waves-effect waves-light btn">Add</a><a class="waves-effect waves-light btn">Remove</a></div>
                                        <div id="currentLP" class="row">

                                        </div>
                                    </div>
                                    <div class="card-action">
                                        <a class="waves-effect waves-teal btn-flat" onclick=newEnsemble()>New</a>
                                        <a class="waves-effect waves-teal btn-flat" onclick=createEnsemble()>Create</a>



                                    </div>
                                </div>
                        </div>

                        <h5>Gestion des ensembles</h5>
                            <div class="card">
                                <div class="card-content white-text">
                                    <p>Gestion des Ensembles</p>
                                    <div id="listPack" class="row">

                                    </div>
                                    <div id="btnPack"><a class="waves-effect waves-light btn">Add</a><a class="waves-effect waves-light btn">Remove</a></div>
                                    <div id="currentPack" class="row">

                                    </div>
                                </div>
                                <div class="card-action">
                                    <a class="waves-effect waves-teal btn-flat" onclick=newEnsemble()>New</a>
                                    <a class="waves-effect waves-teal btn-flat" onclick=createEnsemble()>Create</a>

                                </div>
                            </div>
                    </div>

                </div>
            </div>
        </div>

    </div>

</body>
</html>

