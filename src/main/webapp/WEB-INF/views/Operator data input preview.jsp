<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security' %>
<%@ page session="true" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="cs" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mujtaba Mehdi">
    <title>Operator data input template</title>
    <!-- Custom styles for this template -->
    <!-- Bootstrap core CSS -->
    <!--range slider css-->
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui/1.10.3/jquery-ui.css" />">
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" media="all"> 
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/resources/css/form.css" />" rel="stylesheet"  type="text/css" />		
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" media="screen,projection" />
	<link href="<c:url value="/resources/css/messages/messages.css" />" rel="stylesheet"  type="text/css" />
	
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=drawing,geometry"></script>
	<script src="<c:url value="/resources/js/jquery/1.10/jquery-1.10.2.js" />"></script>  
	<script type="text/javascript" src="<c:url value="/resources/js/geoxml/polys/geoxml3.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/geoxml/ProjectedOverlay.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/jscolor/jscolor.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/json2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/xmlwriter.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/blitz.gmap3.js" />"></script>
	
</head>
<body>

    <div id="layout">
        <div id="header">
			<%@ include file="header.jsp" %>
        </div>
        <div id="main">
            <div id="main-box">
                <div id="quote">
                    "If we could get a system in place...with a common booking entry, 
                    so that somebody could call up a vehicle that was available, irrespective
                    of whether that was actually in the ownership of the health board, the council, 
                    the third sector, then it might lead to more viability, more sustainability 
                    all round...That is what we should be aiming for."
                    <span style = "font-size: 14px;">---  Audit Scotland, 2011.</span>
                </div>
            </div>
            <div id="input-content">
          
                <div class="container">
				
                    <div class="row">

        <form:form id="data_input_form" action="operator_data_input_constraint_preview_submit" method="post" modelAttribute="operatorDataInputForm" >

            <div class="col-md-10 col-lg-8">
                <h1> Preview</h1>
               <div class="panel-group" id="accordion">

                  <div class="panel panel-info" id="tab-general">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <!--  <a data-toggle="" data-parent="#accordion" href="#collapseOne"> -->
                            General Information
                        <!--  </a>  -->
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in">
                  <div class="panel-body">
                      <!--form class="form-horizontal" role="form" id="tab-general-form"-->
                        <div class="form-group col-md-12 col-lg-12 col-sm-12">
                            <label for="tab-general-field-1" class="col-sm-4 control-label">Service Name: </label>
                            <div class="col-sm-10">
                                <p  class="col-sm-8 summary-label">
                                <c:if test="${not empty service_name}">
								    <c:out value="${service_name}"></c:out>
								</c:if>
                                
                                </p>

                            </div>
                        </div>
                        <div class="form-group col-md-12 col-lg-12 col-sm-12">
                            <label for="tab-general-field-2" class="col-sm-4 control-label">Service Description: </label>
                            <div class="col-sm-10">
                               
                               
                               <p rows="5" cols="5" class="col-sm-8 summary-label" id="tab-general-field-2" name="tab-general-field-2">
                               <c:if test="${not empty service_description}">
								    <c:out value="${service_description}"></c:out>
								</c:if>
                              
                               </p>   
                           </div>
                       </div>


                        <div class="form-group col-md-12 col-lg-12 col-sm-12">
                            <label for="tab-general-field-2" class="col-sm-4 control-label">Operating Hours: </label>

                        <table id="schedule-preview">       
                            <thead>         
                                <tr>         
                                 <th>Day</th>              
                                 <th>Opening Time</th>               
                                 <th>Closing Time</th>           
                             </tr>       
                         </thead>        
                         <tbody>     
                            <c:if test="${not empty opening_hours}">
								    ${opening_hours}
							</c:if>
                        </tbody> 
                    </table>


                </div>
            <!--/form--> 
        </div>
        </div>
    </div> <!--first-->
    
    <!-- operating area -->

    <div class="panel panel-info" id="tab-operatin-area">
        <div class="panel-heading">
          <h4 class="panel-title">
           <!--   <a data-toggle="" data-parent="#accordion" href="#collapseTwo"> -->
                Operating area
           <!--  </a>  -->
        </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse in">
      <div class="panel-body">
         <!--form class="form form-inline" role="form" id="tab-operating-area-form"-->

            <div class="form-inline-row">
                <div class="col-md-12">
                   
                    <div id="myMap" style="height:400px; width:100%;"></div>
                    <!--  <input type="button" value="Create Map from JSON" onclick="BlitzMap.setMap( 'myMap', true, 'mapData' ); BlitzMap.init()" /> -->

                    <!--input type="button" id = "clearMap" style = "margin: 10 0 0 0;float:right;" value="clear" onclick="BlitzMap.deleteAll();" /-->
                    <!--input type="button" id = "editMap" style = "margin: 10 0 0 0;float:right;width:45px;" value="save"  onclick="editMap();"/-->
                    
                    <!--  <input type="button" value="parse KML to map" onclick="BlitzMap.setMapFromKML(document.getElementById('kmlString').value)" /> -->
                    
                    <textarea id="mapData" style="display:none;" style="">
                     ${jsonData}
                    </textarea>
                   

                </div>
            </div>
            <br/>

            
        <!--/form-->
    </div> <!--panel body-->
</div> <!---->
</div> <!--panel-->


    <div class="panel panel-info" id="tab-eligibility">
        <div class="panel-heading">
          <h4 class="panel-title">
            <!--   <a data-toggle="" data-parent="#accordion" href="#collapseThree"> -->
                Passenger Eligibility
            <!--  </a>  -->
        </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse in">
      <div class="panel-body">
         <!--form class="form" role="form" id="tab-surcharge-structure-form"-->

                <div class="col-lg-12 col-md-12">
                    <fieldset>
                        <legend>Selected Age groups:</legend>
                                 
                      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
                      
                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" for="tab-eligibility-field-1 ">
                                    Under 16 years of age: 
                                </p>
                                <label class="summary-label">
                                	<c:choose>
										  <c:when test="${eligible_checkbox_under16 == '1'}">
										    yes
										  </c:when>
										  <c:otherwise>
										    no
										  </c:otherwise>
									</c:choose>
                               </label>
                            </div>                                    

                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" label-default="" for="tab-eligibility-field-2">
                                   17 to 21 years of age:
                                </p>   
                                <label class="summary-label">      
                                <c:choose>
								  <c:when test="${eligible_checkbox_17_to_21 == '2'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>   
								</label>                
                            </div>
                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" label-default="" for="tab-eligibility-field-3">
                                    22 to 54 years of age:
                                </p>
                                <label class="summary-label">
                                <c:choose>
								  <c:when test="${eligible_checkbox_22_to_54 == '3'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>
								</label>
                            </div>

                    </div>   <!--1st column-->


                      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->

                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" for="tab-eligibility-field-4">
                                    55 to 59 years of age:
                                </p>
                                <label class="summary-label">
                                <c:choose>
								  <c:when test="${eligible_checkbox_55_to_59 == '4'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>
								</label>
                            </div>

                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" for="tab-eligibility-field-5">
                                    60 years or above:
                                </p>
                                <label class="summary-label">
                                 <c:choose>
								  <c:when test="${eligible_checkbox_over60 == '5'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>
								</label>
                            </div>
                        
                        </div>   <!--2nd column-->

                    </fieldset>

                </div>

                <div class="col-lg-12 col-md-12">
                    <fieldset>
                        <legend>Selected mobility status:</legend>

                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
                        
                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                            <p class="summary-label" for="tab-eligibility-field-6">
                                Able bodied:
                            </p>
                            <label class="summary-label">
                            <c:choose>
								  <c:when test="${eligible_checkbox_able_bodied == '6'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
							</c:choose>
							</label>
                        </div>
                        <div class="form-group col-lg-12 col-sm-11 col-md-10">
                            <p class="summary-label" for="tab-eligibility-field-7">
                                Mobility prevents use of regular Public Transport:
                            </p>
                            <label class="summary-label">
                            <c:choose>
								  <c:when test="${eligible_checkbox_mobility_prevents_PT == '7'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
							</c:choose>
							</label>
                        </div>
                    </div>   <!--1st column-->

                      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->

                        <div class="form-group col-lg-12 col-sm-11 col-md-8">
                            <p class="summary-label" for="tab-eligibility-field-8">
                                Disabled wheelchair user:
                            </p>
                            <label class="summary-label">
                            <c:choose>
								  <c:when test="${eligible_checkbox_disable_wheelchair_user == '8'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
							</c:choose>
							</label>
                        </div>
                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                            <p class="summary-label" for="tab-eligibility-field-9">
                                Disabled other:
                            </p>
                            <label class="summary-label">
                            <c:choose>
								  <c:when test="${eligible_checkbox_disable_other == '9'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
							</c:choose>
							</label>
                          </div>
                    </div>   <!--2nd column-->
                    </fieldset>
                </div>

                <div class="col-lg-12 col-md-12">
                    <fieldset>
                        <legend>Selected journey purpose types:</legend>

                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->

                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" for="tab-eligibility-field-10">
                                    Health Appointment:
                                </p>    
                                <label class="summary-label">        
                                <c:choose>
								  <c:when test="${eligible_checkbox_health_appointment == '10'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>  
								</label>  
                            </div>                                    

                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" for="tab-eligibility-field-11">
                                    Social Care:
                                </p>    
                                <label class="summary-label">            
                                 <c:choose>
								  <c:when test="${eligible_checkbox_social_care == '11'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>  
								</label>                          
                            </div>
                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" for="tab-eligibility-field-12">
                                    Shopping:
                                </p>
                                <label class="summary-label">
                                <c:choose>
								  <c:when test="${eligible_checkbox_shopping == '12'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>    
								</label>
                            </div>
                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" for="tab-eligibility-field-13">
                                    Leisure/Visiting Friends
                                </p>
                                <label class="summary-label">
                                <c:choose>
								  <c:when test="${eligible_checkbox_leisure_or_visiting_friends == '13'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>   
								</label>
                            </div>

                        </div> <!--1st column-->
                     
                      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->

                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" for="tab-eligibility-field-14">
                                    School/Education
                                </p>
                                <label class="summary-label">
                                <c:choose>
								  <c:when test="${eligible_checkbox_school_or_education == '14'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>   
								</label>
                            </div>

                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" for="tab-eligibility-field-15">
                                    Work/Commuting
                                </p>
                                <label class="summary-label">
                                <c:choose>
								  <c:when test="${eligible_checkbox_work_or_commuting == '15'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>  
								</label> 
                            </div>

                        <div class="form-group col-lg-12 col-sm-8 col-md-8">
                                <p class="summary-label" for="tab-eligibility-field-16">
                                    Other:
                                </p>
                                <label class="summary-label">
                                 <c:choose>
								  <c:when test="${eligible_checkbox_other_purpose == '16'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>   
								</label>
                            </div>

                        </div> <!--2nd column-->

                    </fieldset>
                </div>
        

    </div> <!--panel body-->
</div> <!--collapse3-->
</div> <!--panel-->


<div class="panel panel-info" id="tab-fare-structure">
    <div class="panel-heading">
      <h4 class="panel-title">
        <!--   <a data-toggle="" data-parent="#accordion" href="#collapseFour"> -->
          Fare Structure
      <!--  </a> -->
  </h4>
</div>
<div id="collapseFour" class="panel-collapse collapse in">
  <div class="panel-body">

    <!--form class="form" role="form" id="tab-fare-structure-form"-->
            <div class="col-lg-12 col-md-12">
            <fieldset>
                <legend>Distance:</legend>
				<div id="fare_table">
				
						 <c:if test="${empty chargeFare}">
						 	<label>You don't charge a standard fare.</label>
						 	
						 </c:if>
						 
						 <c:if test="${not empty chargeFare}">
						 	<div class="row col-lg-12 col-sm-12 col-xs-12">
							  <div class="col-md-3 "><label>From</label></div>
							  <div class="col-md-3"><label>To</label></div>
							  <div class="col-md-3"><label>Type</label></div>
							  <div class="col-md-3"><label>Fare</label></div>
							</div>
							 <c:forEach items="${fareDist4Tuple}" var="tuple" step="1">
							 	<div class="row col-lg-12 col-sm-12 col-xs-12">
								  <div class="col-md-3 "><label>${tuple.getValue1()} mile(s)</label></div>
								  <div class="col-md-3">
								  	<label>${tuple.getValue2()} mile(s)</label>
								  </div>
								  <div class="col-md-3">
								  	<label>${tuple.getValue3()} </label></div>
								  <div class="col-md-3"> 
								  <label>${tuple.getValue4()}</label>
								  </div>
								</div>
							 </c:forEach>
						 </c:if>
						 
						
				</div>
                    

             </fieldset>
             </div>

            <div class="col-lg-12 col-md-12">
            <fieldset>
                <legend>Other:</legend>

                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->

                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                          <label for="tab-fare-structure-dist-field-10" class="col-sm-6 col-xs-6 col-md-6">Return fare multiplier:</label>
                          <div class="col-xs-4">
                                <p class="summary-label" id="tab-fare-structure-dist-field-10">
                                
                                <c:if test="${not empty return_fare_multiplier}">
									<c:out value="${return_fare_multiplier}"></c:out>
								</c:if>
                                 
                                </p>
                        </div>
                        </div>

                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                          <label for="tab-fare-structure-dist-field-11" class="col-sm-6 col-xs-6 col-md-6">Discount % for over 60s:</label>
                          <div class="col-xs-4">
                                <p class="summary-label" id="tab-fare-structure-dist-field-11">
                                	<c:if test="${not empty discount_for_over60}">
										<c:out value="${discount_for_over60}"></c:out>
									</c:if>
                                </p>
                          </div>
                        </div>


                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                          <label for="tab-fare-structure-dist-field-12" class="col-sm-6 col-xs-6 col-md-6">Discount % for under 16s:</label>
                          <div class="col-xs-4">
                                <p class="summary-label" id="tab-fare-structure-dist-field-12">
                                	<c:if test="${not empty discount_for_under16}">
										<c:out value="${discount_for_under16}"></c:out>
									</c:if>
                                </p>
                          </div>
                        </div>

                     </div><!--1st column-->

                      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->


                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        
                                <label class="summary-label" id="tab-fare-structure-dist-field-13">Escorts are charged a fare:
                                </label>
                                <c:choose>
								  <c:when test="${fare_structure_checkbox_escort == 'true'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>  
								
                          </div>

                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                              	
                                <label class="summary-label" id="tab-fare-structure-dist-field-14">This service charge for dead mileage:
                                </label>
                                <c:choose>
								  <c:when test="${fare_structure_checkbox_charge_for_dead_mileage == 'true'}">
								    yes
								  </c:when>
								  <c:otherwise>
								    no
								  </c:otherwise>
								</c:choose>   
                              </div>
                        </div><!--2nd column-->
                          </fieldset>
                        </div>

            <!--/form-->

</div> <!--panel body-->
</div> <!--collapse3-->
</div> <!--panel-->


<div class="panel panel-info" id="tab-surcharge-structure" style="display:none;">
    <div class="panel-heading">
      <h4 class="panel-title">
        <!--   <a data-toggle="" data-parent="#accordion" href="#collapseFive"> -->
          Surcharge Structure
      <!--   </a> -->
  </h4>
</div>
<div id="collapseFive" class="panel-collapse collapse in">
  <div class="panel-body">
   <p> <h2> Surcharges</h2><span>Please enter your penalty surcharges for operating outside of your usual boundaries and operating time, and for transporting passengers who are not normally eligible. </span></p>
   <!--form class="form form-inline-row" role="form" id="tab-surcharge-structure-form"-->

            <div class="col-lg-12 col-md-12">
            <fieldset>
                <legend>Distance:</legend>

                    <div id="surcharge_table">
				
						<div class="row col-lg-12 col-sm-12 col-xs-12">
							  <div class="col-md-3 "><label>From</label></div>
							  <div class="col-md-3"><label>To</label></div>
							  <div class="col-md-3"><label>Type</label></div>
							  <div class="col-md-3"><label>Surcharge</label></div>
						</div>
						
						 <c:forEach items="${surDist4Tuple}" var="tuple" step="1">
							 	<div class="row col-lg-12 col-sm-12 col-xs-12">
								  <div class="col-md-3 "><label>${tuple.getValue1()} mile(s)</label></div>
								  <div class="col-md-3">
								  	<label>${tuple.getValue2()} mile(s)</label>
								  </div>
								  <div class="col-md-3">
								  	<label>${tuple.getValue3()} </label>
								  	</div>
								  <div class="col-md-3"> 
								  <label>${tuple.getValue4()}</label>
								  </div>
								</div>
							 </c:forEach>
							 
							 
				</div>

                 </fieldset>
            </div>


            <!--div class="col-lg-12 col-md-12">
            <fieldset>
                <legend>Other:</legend>

                      
                                          <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                          <label for="tab-surcharge-structure-dist-field-9" class="col-sm-6 col-xs-6 col-md-6">Return fare multiplier:</label>
                          <div class="col-xs-4">
                            <input type="text" class="form-control" id="tab-surcharge-structure-dist-field-9" placeholder="£0.0">
                        </div>
                        </div>

                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                          <label for="tab-surcharge-structure-dist-field-10" class="col-sm-6 col-xs-6 col-md-6">Discount % for over 60s:</label>
                          <div class="col-xs-4">
                            <input type="text" class="form-control" id="tab-surcharge-structure-dist-field-10" placeholder="%0.0">
                        </div>
                        </div>


                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                          <label for="tab-surcharge-structure-dist-field-11" class="col-sm-6 col-xs-6 col-md-6">Discount % for under 16s:</label>
                          <div class="col-xs-4">
                            <input type="text" class="form-control" id="tab-surcharge-structure-dist-field-11" placeholder="%0.0">
                        </div>
                        </div>

                     </div>

                      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">


                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                          <label for="tab-surcharge-structure-dist-field-12" class="col-sm-12 col-xs-12 col-md-12">
                              <input type="checkbox" name="surcharge-structure-checkbox" id="tab-surcharge-structure-dist-field-12"> Are escorts charged a fare?</label>
                          </div>

                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                              <label for="tab-surcharge-structure-dist-field-13" class="col-sm-12 col-xs-12 col-md-12">
                                  <input type="checkbox" name="surcharge-structure-checkbox" id="tab-surcharge-structure-dist-field-13"> Does this service charge for dead mileage?</label>
                              </div>

                        </div>

                          </fieldset>
                        </div-->



            <div class="col-lg-12 col-md-12">
            <fieldset>
            <legend>Passenger age group:</legend>

                      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
                      <c:forEach items="${surAgeGrp}" var="pair" step="2">
                      		<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-group-1">
		                     <label for="tab-surcharge-structure-pt-field-1" class="col-sm-6 col-xs-6 col-md-6">${pair.getText()}</label>
		                    <div class="col-xs-4">
		                        <p class="summary-label" id="tab-surcharge-structure-pt-field-1">${pair.getValue()}</p>
		                     </div>
		                    </div>
                      </c:forEach>
                     </div><!--1st column-->
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
                   		  <c:forEach items="${surAgeGrp}" var="pair" begin="1" step="2">
                   		   <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-group-4">
		                      <label for="tab-surcharge-structure-pt-field-4" class="col-sm-6 col-xs-6 col-md-6">${pair.getText()}</label>
		                      <div class="col-xs-4">
		                        <p class="summary-label" id="tab-surcharge-structure-pt-field-4">${pair.getValue()}</p>
		                    	</div>
		                    </div>
                      </c:forEach>
                    </div> <!--2nd column-->
                </fieldset>
            </div>



        <div class="col-lg-12 col-md-12">
            <fieldset>
            <legend>Passenger mobility status:</legend>

                      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->

                 			<c:forEach items="${surMobStats}" var="pair" step="2">
	                      		<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-group-1">
			                     <label for="tab-surcharge-structure-pt-field-1" class="col-sm-6 col-xs-6 col-md-6">${pair.getText()}</label>
			                    <div class="col-xs-4">
			                        <p class="summary-label" id="tab-surcharge-structure-pt-field-1">${pair.getValue()}</p>
			                     </div>
			                    </div>
                      		</c:forEach>
                 
                     </div><!--1st column-->

                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
                   			 <c:forEach items="${surMobStats}" var="pair" begin="1" step="2">
		                   		   <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-group-4">
				                      <label for="tab-surcharge-structure-pt-field-4" class="col-sm-6 col-xs-6 col-md-6">${pair.getText()}</label>
				                      <div class="col-xs-4">
				                        <p class="summary-label" id="tab-surcharge-structure-pt-field-4">${pair.getValue()}</p>
				                    	</div>
				                    </div>
		                      </c:forEach>
                    </div> <!--2nd column-->

                </fieldset>
            </div>
       
        <div class="col-lg-12 col-md-12">
            <fieldset>
            <legend>Passenger journey purpose:</legend>

                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
							<c:forEach items="${surJrnyPurp}" var="pair" step="2">
	                      		<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-group-1">
			                     <label for="tab-surcharge-structure-pt-field-1" class="col-sm-6 col-xs-6 col-md-6">${pair.getText()}</label>
			                    <div class="col-xs-4">
			                        <p class="summary-label" id="tab-surcharge-structure-pt-field-1">${pair.getValue()}</p>
			                     </div>
			                    </div>
                      		</c:forEach>
                     </div><!--1st column-->

                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
                   		 <c:forEach items="${surJrnyPurp}" var="pair" begin="1" step="2">
		                   		   <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-group-4">
				                      <label for="tab-surcharge-structure-pt-field-4" class="col-sm-6 col-xs-6 col-md-6">${pair.getText()}</label>
				                      <div class="col-xs-4">
				                        <p class="summary-label" id="tab-surcharge-structure-pt-field-4">${pair.getValue()}</p>
				                    	</div>
				                    </div>
		                      </c:forEach>
                    </div> <!--2nd column-->
                </fieldset>
            </div>


        <div class="col-lg-12 col-md-12">
            <fieldset>
            <legend>Time:</legend>

                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->

                        <p class="col-xs-offset-2 col-sm-offset-2 col-md-offset-2 col-lg-offset-2"><strong>Start</strong></p>
                    <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                    <label for="tab-surcharge-structure-time-field-1" class="col-sm-6 col-xs-6 col-md-6">Start 0.5 hours earlier:</label>
                    <div class="col-xs-4">
                        <p class="summary-label" id="tab-surcharge-structure-time-field-1">
                        	<c:if test="${not empty surcharge_start_05_hours_earlier}">
								<c:out value="${surcharge_start_05_hours_earlier}"></c:out>
							</c:if>
                        </p>
                    </div>
                     </div>

                    <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                      <label for="tab-surcharge-structure-time-field-2" class="col-sm-6 col-xs-6 col-md-6">Start 1 hour earlier:</label>
                      <div class="col-xs-4">
                        <p class="summary-label" id="tab-surcharge-structure-time-field-2">
                        	<c:if test="${not empty surcharge_start_1_hour_earlier}">
								<c:out value="${surcharge_start_1_hour_earlier}"></c:out>
							</c:if>
                        </p>
                    </div>
                    </div>

                    <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                      <label for="tab-surcharge-structure-time-field-3" class="col-sm-6 col-xs-6 col-md-6">Start 1.5 hours earlier:</label>
                      <div class="col-xs-4">
                        <p class="summary-label" id="tab-surcharge-structure-time-field-3">
                        	<c:if test="${not empty surcharge_start_15_hours_earlier}">
								<c:out value="${surcharge_start_15_hours_earlier}"></c:out>
							</c:if>
                        </p>
                        </div>
                    </div>

                     </div><!--1st column-->

                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
                    <p class="col-xs-offset-2 col-sm-offset-2 col-md-offset-2 col-lg-offset-2"><strong>Finish</strong></p>
                    <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                     <label for="tab-surcharge-structure-time-field-4" class="col-sm-6 col-xs-6 col-md-6">Finish 0.5 hours later:</label>
                          <div class="col-xs-4">
                        <p class="summary-label" id="tab-surcharge-structure-time-field-4">
                        	<c:if test="${not empty surcharge_finish_05_hours_later}">
								<c:out value="${surcharge_finish_05_hours_later}"></c:out>
							</c:if>
                        </p>
                        </div>
                    </div>

                    <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                      <label for="tab-surcharge-structure-time-field-5" class="col-sm-6 col-xs-6 col-md-6">Finish 1 hour later:</label>
                      <div class="col-xs-4">
                        <p class="summary-label" id="tab-surcharge-structure-time-field-5">
                       	 	<c:if test="${not empty surcharge_finish_1_hour_later}">
								<c:out value="${surcharge_finish_1_hour_later}"></c:out>
							</c:if>
                        </p>
                    </div>
                    </div>
                    <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                      <label for="tab-surcharge-structure-time-field-6" class="col-sm-6 col-xs-6 col-md-6">Finish 1.5 hours later:</label>
                      <div class="col-xs-4">
                        <p class="summary-label" id="tab-surcharge-structure-time-field-6">
                        	<c:if test="${not empty surcharge_finish_15_hours_later}">
								<c:out value="${surcharge_finish_15_hours_later}"></c:out>
							</c:if>
                        </p>
                    </div>
                    </div>

                
                    </div> <!--2nd column-->

                </fieldset>
            </div>


        <!--/form-->



        </div> <!--panel body-->
        </div> <!--collapse3-->
        </div> <!--panel-->

    </div>
    
    			<div class="row col-lg-12 col-sm-12 col-xs-12">
					  <div class="col-md-4" >
					  	<button class="btn-lg btn-primary" onclick="window.print();return false;" style="margin: 0px 0px 0px 20px;">Print</button>
					  	<!--  <button  class="btn-lg btn-primary" onclick="history.go(-1);">Edit</button>-->
					  	<button  class="btn-lg btn-primary" onclick="document.location.href = '/operator_data_input_constraint_edit'">Edit</button>
					  
					  	
					  	
					  </div>
					  <div class="col-md-2">
					  	
					  </div>
					  <div class="col-md-4">
					  	
					  
					  </div>
					  <div class="col-md-2">
					 	<button type="submit" class="btn-lg btn-success">Submit</button>
					  </div>
				</div>
				
            
            <!-- <a href="javascript:window.print()"><img src="<c:url value="/resources/img/click-here-to-print.jpg" />" alt="print this page" id="print-button" /></a>
             -->
           <!--save clear button-->

    </div>
</form:form>

    </div>
<!-- /.row -->



</div><!-- /.container -->


</div>
</div>
<div id="footer">

 <p id="copyright">
    <span>&copy; Copyright 2013 - University of Aberdeen <br />Flexible Integrated Transport System Project<br />
        MacRobert Building, King's College, Aberdeen, AB24 5UA<br />
        t: +44 (0) 1224 274065 e: fits@abdn.ac.uk
    </span>
</p>  




<div class="inline"><img id = "de" src="<c:url value="/resources/img/de.jpg" />" /></div>
<div class="inline"> <img id = "abdn" src="<c:url value="/resources/img/abdn.gif" />" /></div>


</div>
</div>

<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- <script src="<c:url value="/resources/jquery/1.10/jquery-1.10.2.js" />"></script>   -->
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>

    <!-- Custom JavaScript-->

 	<script src="<c:url value="/resources/js/jqueryui/1.10/jquery-ui-1.10.js" />"></script>
    <!--  script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script-->

    <script src="<c:url value="/resources/js/FITS.js" />"></script> 
    
    <script type="text/javascript">


    var isConfirmed = false;
    function editMap(){
        if(isConfirmed){
            BlitzMap.toggleEditable();
            $("#editMap").val("save");
            //$('#mapData').val("");
            //$('#jsonData').val("");
            isConfirmed = false;
        }else{
            BlitzMap.toggleEditable();
            isConfirmed = true;
            BlitzMap.toJSONString();
            $('#jsonData').val($('#mapData').val());
            BlitzMap.toKML();
            $('#kmlData').val($('#kmlString').val());
            $("#editMap").val(" edit ");
        }
    }
    
    
	function init() {
		BlitzMap.setMap( 'myMap', true, 'mapData' );
		BlitzMap.init();// initialize BlitzMap
		BlitzMap.toggleEditable();// swith not to be editable
	}
	
	
//fix the map showing problem
window.onload = function() {
    //$("#collapseTwo").removeClass("in");
   // $("#collapseOne").addClass("in");
   
	 BlitzMap.setMap( 'myMap', true, 'mapData' );
	 BlitzMap.init();// initialize BlitzMap
		BlitzMap.toggleEditable();// swith not to be editable
}

      </script>


</body>
</html>