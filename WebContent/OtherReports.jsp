					<script> 
					  	var myColumns = ["Report","Department","Criteria","","Short Code","Module Sub Product Wise"]
					  	
                        var str=new AW.Formats.String;
						var grid=createBrowser('printOptions',myColumns,[str,str,str,str,str,str]);
						grid.setColumnIndices([0,4,1,2,5])
						var data = [["Age Analysis Of Lease Outstanding","Credit Admin","Report Date / Given Criteria",1,"OR26","Yes"],
									["Lease Portfolio - Sector","Credit Admin","Report Date / Given Criteria",27,"OR73","Yes"],
									["Lease Portfolio - Sub Sector","Credit Admin","Report Date / Given Criteria",48,"OR74","Yes"],
									["Exposure by Stakeholder","Credit Admin","Report Date / Given Criteria",28,"OR76","Yes"],
									["Overpayment","Branch Credit","Report Date / Given Criteria",30,"OR20","No"]
								];
                            document.write(grid);
                            grid.onSelectedRowsChanged = function(row){
                 			document.getElementById("selected").value=this.getCellText(3,row);
                        };
                      	grid.onRowDoubleClicked = function(event,row){
                          	document.getElementById("selected").value=this.getCellText(3,row);
                          	if(confirm("Confirm to Open the Excel ?")){
                          		createExcel($('selected').value,0,0)
                          	}
                        }
                    	setGridData(grid,data)   
               		</script>
