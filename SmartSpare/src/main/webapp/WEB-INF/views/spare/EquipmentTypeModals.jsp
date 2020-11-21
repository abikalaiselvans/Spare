<div class = "container">

<!-- Model For Add New Equipment Type -->
<div class = "modal fade" id = "addNewEquipmentTypeModal"  data-backdrop= 'static' role = "dialog">
	<div class = "modal-dialog">
		<div class = "modal-content">
            <form class = "form-horizontal" action = "AddEquipementType" method="post" name="addequTypeForm">
                <div class = "modal-header">
				    <h4 class="modal-title">EQUIPMENT TYPE</h4>
			     </div>
			     <div class = "modal-body">
				    <div class="form-group">
                        <label for = "equipmentTypeName" class = "control-label col-md-4">Equipment Type<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                                <input class = "form-control uCaptilized" id = "name" placeholder = "Enter the Equipment Type" name="name" required maxlength='100'  >
                                <script type="text/javascript">searchItem("name","addEquipmentType","spare_m_equipement_type","CHR_EQUIPEMENTTYPENAME");</script>
                        </div>
                    </div>
                     
                     <div class="form-group">
                        <label for = "equipmentTypeDesc" class = "control-label col-md-4">Description<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                                <textarea class="form-control uCaptilized " rows="7" cols='30'  maxlength='500' id = "description" name = "description" required placeholder = "Enter the description"></textarea>
                        </div>
                    </div>
			     </div>
			    <div class = "modal-footer">
				    <button class="btn btn-primary" type="submit" id = "addEquipmentType"><span class="glyphicon glyphicon-ok"></span>&nbsp;Add</button>
				    <a href = "#" type = "button" class = "btn btn-default madalClose" data-dismiss = "modal"><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</a>
                    
			    </div>
            </form>    
                    
		</div>
	</div>
</div>


<!-- Model For Edit  Equipment Type -->
<div class = "modal fade" id = "editEquipmentTypeModal" role = "dialog"   data-backdrop= 'static' >
	<div class = "modal-dialog">
		<div class = "modal-content">
            <form class = "form-horizontal" action="UpdateEquipmentType" method = "post">
                <div class = "modal-header">
				   <h4 class="modal-title">EQUIPMENT TYPE <input type='hidden' name='erowid' id='erowid' value='0'/></h4>
			     </div>
			     <div class = "modal-body">
				    <div class="form-group">
                        <label for = "editEquTypeName" class = "control-label col-md-4">Equipment Type<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                                <input  class = "form-control text-uppercase" id="ename" name="name"  value="" required  placeholder = "Enter the Equipment Type"  maxlength='100' />
                                 
                      
                        </div>
                    </div>
                     
                     <div class="form-group">
                        <label for = "editEquTypeDesc" class = "control-label col-md-4">Description<span class = "star"> *</span></label>
                        <div class = "col-md-7">
                                <textarea class="form-control  uCaptilized" cols='30'  maxlength='500'  id="edescription"  name="description" required>&nbsp;</textarea>
                        </div>
                    </div>
			     </div>
			    <div class = "modal-footer">
				    <button class="btn btn-primary" type="submit" id = "editEquipmentType" ><span class="glyphicon glyphicon-ok"></span>&nbsp;Update</button>
				    <a href = "#" class = "btn btn-default madalClose" data-dismiss = "modal" ><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</a>
			    </div>
            </form>    
                    
		</div>
	</div>
</div>


<!-- Modal for Equipment close -->
<div id="equTypeConformCloseModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close madalClose" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Confirmation</h4>
            </div>
            <div class="modal-body">
                <p>Are you really want to close ? </p>
                <p class="text-warning"><small>If you close, you can't add your new record.</small></p>
            </div>
            <div class="modal-footer">
                
                <button type="button" class="btn btn-primary" id = "equTypeCloseProceed"><span class="glyphicon glyphicon-ok"></span>&nbsp;Proceed</button>
                <button type="button" class="btn btn-default madalClose" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>&nbsp;Cancel</button>
                
            </div>
        </div>
    </div>
</div>

<!-- Modal for Equipment delete  -->
<div id="equTypeConformDeleteModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal madalClose" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Confirmation</h4>
            </div>
            <div class="modal-body">
                <p>Are you really want to delete ? </p>
                <p class="text-warning"><small>If you delete, you loss the selected records</small></p>
            </div>
            <div class="modal-footer">
                
                <button type="button" class="btn btn-primary" id = "equTypeDeleteProceed"><span class="glyphicon glyphicon-ok"></span>&nbsp;Proceed</button>
                <button type="button" class="btn btn-default madalClose" data-dismiss="modal" id = 'equTypeDeleteClose'><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</button>
                
            </div>
        </div>
    </div>
</div>



</div>
