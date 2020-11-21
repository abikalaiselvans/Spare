<div class = "container">

<!-- Model For Add and Edit  --> 
<div class = "modal fade" data-backdrop='static' id = "showAddEditModel" role = "dialog">
	<div class = "modal-dialog">
		<div class = "modal-content">
            <form class = "form-horizontal" id ="AddEditFormModel" action = "" method="post" name="frm">
                <div class = "modal-header">
				    <h4 class="modal-title">EQUIPEMENT MODEL</h4>
			     </div>
			     <div class = "modal-body">
			      <div class="form-group">
                        <label for = "equipmentTypeName" class = "control-label col-md-4">Type<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                                <select name='equipementtypeid' id='eequipementtypeid'  class="form-control" required>
									<option value=''>Select type</option>
									<c:forEach var="row" items="${equipmentType}"  varStatus="counter">
										<option value='${row.rowid}' >${row.name} </option>
									</c:forEach>
								</select>
                        </div>
                    </div>
                    
                    
                    <div class="form-group">
                        <label for = "equipmentMakeName" class = "control-label col-md-4">Make<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                                <select name='equipementmakeid' id='eequipementmakeid'  class="form-control" required>
									<option value=''>Select Make</option>
									<c:forEach var="row" items="${equipmentMake}"  varStatus="counter">
										<option value='${row.rowid}' >${row.name} </option>
									</c:forEach>
								</select>
								
                        </div>
                    </div>
                    
				    <div class="form-group">
                        <label for = "equipmentTypeName" class = "control-label col-md-4">Model<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                                <input class = "form-control text-uppercase" id = "name" placeholder = "Enter Equipment Model" name="name" required maxlength='100'>
                                 <script type="text/javascript">searchItem("name","formButton","spare_m_equipement_model","CHR_MODEL");</script>
                                <input type='hidden' name='erowid' id ='erowid' value=''/>
                        </div>
                    </div>
                     
                     <div class="form-group">
                        <label for = "equipmentTypeDesc" class = "control-label col-md-4">Description<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                                <textarea class="form-control  uCaptilized" rows="7" cols='30'  maxlength='500' id = "description" name = "description"  placeholder = "Enter the description" required></textarea>
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
