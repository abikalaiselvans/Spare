<div class = "container">

<!-- Model For Add and Edit  --> 
<div class = "modal fade" data-backdrop='static' id = "showAddEditModel" role = "dialog">
	<div class = "modal-dialog">
		<div class = "modal-content">
            <form class = "form-horizontal" id ="AddEditFormModel" action = "" method="post" name="frm">
                <div class = "modal-header">
				    <h4 class="modal-title">PART MASTER</h4>
			     </div>
			     <div class = "modal-body">
			      
                    
                    
				    <div class="form-group">
                        <label for = "equipmentTypeName" class = "control-label col-md-4">Part Number<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                                <input class = "form-control uCaptilized" id = "name" placeholder = "Enter the part number" name="name" maxlength='100' required>
                               <script type="text/javascript">searchItem("name","formButton","spare_m_partmaster","CHR_PARTNO");</script>
                                <input type='hidden' name='erowid' id ='erowid' value=''/>
                                
                        </div>
                    </div>
                     
                     
                     <div class="form-group">
                        <label for = "equipmentTypeName" class = "control-label col-md-4">OEM Part Number<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                                <input class = "form-control uCaptilized" id = "oemname" placeholder = "Enter the OEM part number" name="oemname" maxlength='100' required>
                                 
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label for = "equipmentTypeDesc" class = "control-label col-md-4">Description<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                                <textarea class="form-control  uCaptilized" rows="5"  cols="50" maxlength="500" id = "description" name = "description"  placeholder = "Enter the description" required ></textarea>
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label for = "technicalDesc" class = "control-label col-md-4">Technical<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                        
                        <sql:query var="rstechnicalgroup" dataSource="jdbc/spring">SELECT INT_TECHNICALROUPID, CHR_TECHNICALGROUP  from spare_m_technicalgroup ORDER BY CHR_TECHNICALGROUP</sql:query>
    
                                <select id='technicalgroupid' name='technicalgroupid' required class="form-control">
                                <option value=''>Select the technical group </option>
                                <c:forEach var="data" items="${rstechnicalgroup.rows}">
					                <option value='<c:out value="${data.INT_TECHNICALROUPID}" />'><c:out value="${data.CHR_TECHNICALGROUP}" /></option>
					                 
					            </c:forEach>
					            
                                </select>
                                
                              
                              
                         </div>
                    </div>
                    
                    
                    <div class="form-group">
                        <label for = "generalgroup" class = "control-label col-md-4">Model<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                        	<sql:query var="rsmodel" dataSource="jdbc/spring">SELECT INT_INT_EQUIPEMENTMODELID,CHR_MODEL FROM spare_m_equipement_model ORDER BY CHR_MODEL</sql:query>
    
                                <select id='modelid' name='modelid' required class="form-control">
                                <option value=''>Select the model</option>
                                <c:forEach var="data" items="${rsmodel.rows}">
					                <option value='<c:out value="${data.INT_INT_EQUIPEMENTMODELID}" />'><c:out value="${data.CHR_MODEL}" /></option>
					                 
					            </c:forEach>
					            
                                </select>
                        </div>
                    </div>
                    
                    
                    <div class="form-group">
                        <label for = "generalgroup" class = "control-label col-md-4">General Group<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                        	<sql:query var="rsgenericgroup" dataSource="jdbc/spring">SELECT INT_GENERALGROUPID,CHR_GENERALGROUP FROM spare_m_generalgroup ORDER BY CHR_GENERALGROUP</sql:query>
    
                                <select id='generalgroupid' name='generalgroupid' required class="form-control">
                                <option value=''>Select the Generalgroup</option>
                                <c:forEach var="data" items="${rsgenericgroup.rows}">
					                <option value='<c:out value="${data.INT_GENERALGROUPID}" />'><c:out value="${data.CHR_GENERALGROUP}" /></option>
					                 
					            </c:forEach>
					            
                                </select>
                        </div>
                    </div>
                    
                    
			     </div>
			    <div class = "modal-footer">
				    <button class="btn btn-primary showAction" type="submit" id = "formButton"><span class="glyphicon glyphicon-ok"></span>&nbsp; </button>
				    <a href = "#" type = "button" class = "btn btn-default madalClose" data-dismiss = "modal"><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</a>
                    
			    </div>
            </form>    
                    
		</div>
	</div>
</div>






<!-- Modal for  delete  -->
<div id="" class="modal fade in conformDialog"  data-backdrop='static'>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close madalClose" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Confirmation</h4>
            </div>
            <div class="modal-body">
                <p id = "content">Are you really want to delete ?</p>
                <p ><small id="subContent">If you delete, you loss the selected records</small></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary proceedId" id = "">Proceed</button>
                <button type="button" class="btn btn-default madalClose" data-dismiss="modal" id = 'equTypeDeleteClose'>Cancel</button>
            </div>
        </div>
    </div>
</div>

 
 
 
 
  

</div>
