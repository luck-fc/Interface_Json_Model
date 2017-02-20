#!/usr/bin/python
# -*- coding: UTF-8 -*-
import json
import types
import urllib2
import os
import shutil
import sys 

reload(sys)
sys.setdefaultencoding('utf-8')

#读取配置
config_file = open('model/config.txt','r');
url,classname,importlistitemclass,importjsonutilclass,package='http://www.weather.com.cn/data/sk/101010100.html','Model','top.goluck.tojson.model.ListItem','top.goluck.tojson.util.JsonUtil','top.goluck.interface_json_mdoel'
var1,var2,var3,var4='','','',''
numi = 0
for line in config_file:
	numi+=1
	if(numi==1):
		var1 = line
	if(numi==2):
		var2 = line
	if(numi==3):
		var3 = line
	if(numi >=4):
		var4 += line		
if(var1.find("url=")>=0):
	url = var1[(len("url=")):].strip()
if(var2.find("classname=")>=0):
	classname = var2[(len("classname=")):].strip()
if(var3.find("package=")>=0):
	package = var3[(len("package=")):].strip()
if(var4.find("json=")>=0):
	var4 = var4[(len("json=")):].strip()
# 获取网络数据
def registerUrl():
	try: 
		global url
		print '访问的接口是'+url
		json_str =urllib2.urlopen(url).read()
		json_str = unicode(json_str)
		return json_str;
	except Exception,e:  
		print '接口访问报错了'+ str(e) 	
#将 json_str 转换为 dict 对象 
json_txt = unicode(var4) #var4.decode('gbk').encode('utf8')
if(url.find("http")>=0):
	json_txt = registerUrl()
data = json.loads(json_txt)
# 自定义方法遍历dict对象，并判断数据类型同时存入文件
def praseJson(data,classname,package):
	classname = classname[:1].upper()+classname[1:] #classname.capitalize()
	data_model = ['','']
	data_model[0] = '\n\n    @Override\n    public void praseFromJson(JsonUtil jsonUtil) {'
	data_model[1] = ''
	# 打开 temp.java 文件
	json_file = open("test/temp.java", "r")
	# 复制 temp.java 到 classname.java
	shutil.copy("test/temp.java",  "model/" + classname + ".java")
	create_java_file = open("model/"+classname +".java",'w')
	print("正在生成"+str(classname)+".java文件")
	isimport = False
	if isinstance(data, list):
		data_model1 = data[0]
		for key, value in data_model1.items():
			if isinstance(value, list):
				create_java_file.writelines("package "+package+";\n\nimport java.util.List;\n")
				isimport = True
				break
	else:
		for key, value in data.items():
				if isinstance(value, list):
					create_java_file.writelines("package "+package+";\n\nimport java.util.List;\n")
					isimport = True
					break
	create_java_file.writelines(("" if isimport else "package "+package+(";\n\n"))+"import "+importjsonutilclass+";\n"+"import "+importlistitemclass+";\n")
	create_java_file.close()
	# 获取新建的 classname.java 文件
	new_java_file = open("model/"+ classname +".java",'a');
	# 写入内容
	new_java_file.writelines("\npublic class " + classname + " implements ListItem {"+'\n\n')
	def fordict(javafile,data,modelname):
		data_model1 = data
		if isinstance(data, list):
			data_model1 = data[0]
		for key, value in data_model1.items():
			jsonkey = key
			key = key.capitalize()
			attributekey =  key.lower()
			if isinstance(value, dict):
				praseJson(value,key,package)
				data_model[0] += '\n        set'+str(key)+'(jsonUtil.getT("'+str(jsonkey)+'",new '+str(key)+'()));'
				data_model[1] += '\n    public void set'+str(key)+'('+ str(key)+' '+str(attributekey)+') {\n        this.m'+ str(key) +' = '+ str(attributekey)+ ';\n    }\n';
				data_model[1] += '\n    public '+ str(key)+' get'+str(key)+'() {\n        '+'return this.m'+ str(key)+ ';\n    }\n';
				javafile.writelines('    private '+ str(key) +' m'+str(key)+';'+'\n')
			elif isinstance(value, list):
				praseJson(value,key,package)
				data_model[0] += '\n        set'+str(key)+'(jsonUtil.getList("'+str(jsonkey)+'",new '+str(key)+'()));'
				data_model[1] += '\n    public void set'+str(key)+'(List<'+ str(key)+'> '+str(attributekey)+') {\n        this.m'+ str(key) +' = '+ str(attributekey)+ ';\n    }\n';
				data_model[1] += '\n    public List<'+ str(key)+'> get'+str(key)+'() {\n        '+'return this.m'+ str(key)+ ';\n    }\n';
				javafile.writelines('    private List<'+ str(key) +'> m'+str(key)+';'+'\n')
			else:
				if modelname is None:
					if value and (type(value) != type(1)):
						value=value.encode()
					print("当前生成的属性"+str(key)+"的数据类型是："+str(type(value)))
					type_v=''
					type_type = 'String'
					if type(value) == type(1):
						type_v = 'int'
						type_type = 'Int'		
					elif(type(value) is types.BooleanType):
						type_v ='Boolean'
						type_type = 'Boolean'
					elif type(value) == type(''):
						type_v ='String'
						type_type = 'String'
					else:
						type_v = 'String'
						type_type = 'String'
					data_model[0] += '\n        set'+str(key)+'(jsonUtil.get'+str(type_type)+'("'+str(jsonkey)+'"));'
					data_model[1] += '\n    public void set'+str(key)+'('+ str(type_v)+' '+str(attributekey)+') {\n        this.m'+ str(key) +' = '+ str(attributekey)+ ';\n    }\n';
					data_model[1] += '\n    public '+ str(type_v)+' get'+str(key)+'() {\n        '+'return this.m'+ str(key)+ ';\n    }\n';
					javafile.writelines('    private '+ str(type_v) +' m'+str(key)+';'+'\n')
	fordict(new_java_file,data,None)
	data_model[0] += '\n    }\n'
	new_java_file.writelines("\n    @Override\n    public " + classname + " newObject() {\n        return new " + classname + "();\n    }")
	new_java_file.writelines(data_model[0])
	new_java_file.writelines(data_model[1])
	new_java_file.writelines("\n}")
	# 关闭文件
	new_java_file.close()
praseJson(data,classname,package)
print('\n生成model完毕！')
print('\n生成的model实体类在当前目录的model目录下！')