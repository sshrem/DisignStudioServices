import cloudinary
import cloudinary.uploader
import cloudinary.api
import urllib
import time

downloadDir="/Users/shrem/Movies/design/"
roomOffering=['','','','','']
projectId = 3
supplierId=1
# aptIds=[1,2,3,4]
aptIds=[2]
livingRoomOffering=[5,6,13]
# bedroomOffering=[5,6,13,14]
#bathroomOffering=[10,11,12]
bathroomOffering=[10]
kitchenOffering=[21,23,24]
roomIds=[2,1,3,4]
cloudinaryPath = "projects/shikunbinuidpchk/";
cloudinaryPath2 = "video:projects:shikunbinuidpchk:";

def getVideoValue(aptId, supplierId, roomId, offeringId):
	roomOffering[roomId] = str(offeringId);
	# kitchen - living room and kitchen in the same video
	if roomId == 4:
		return "_" + str(roomId) + "_" + str(offeringId);
	

	path = cloudinaryPath2 +  str(aptId) + ":";
	# living room - the first video
	if roomId == 2:  
		path = cloudinaryPath + str(aptId) + "/";

	
	path = path + str(supplierId) + "_" + str(roomId) + "_" + str(offeringId);

	return path;

def downloadVideo (videoFile1,videoFile2,videoFile3, index):
	print(videoFile1)
	print(videoFile2)
	print(videoFile3)
	videoUrl = cloudinary.utils.cloudinary_url(videoFile1, resource_type= 'video', format= 'webm', transformation=[{'overlay': videoFile2, 'flags': "splice"}, {'overlay': videoFile3, 'flags': "splice"}])
	print(videoUrl[0])
	video =urllib.FancyURLopener()
	video.retrieve(videoUrl[0], downloadDir +  '1.webm')


index=1;
try: 
	for aptIdx, apt in enumerate(aptIds):
		for lRoom in livingRoomOffering:
				for bathRoom in bathroomOffering:
					for kRoom in kitchenOffering:
						print('\n{} - {} -  {}, {}, {}'.format(index, apt, lRoom, bathRoom, kRoom))
						videoFile1 = getVideoValue(apt,supplierId,2,lRoom) + getVideoValue(apt,supplierId,4,kRoom)
						videoFile2 = getVideoValue(apt,supplierId,1,lRoom)
						videoFile3 = getVideoValue(apt,supplierId,3,bathRoom)
						downloadVideo(videoFile1,videoFile2,videoFile3, index)
						index=index+1
except IOError as e:
	print("Error({0}): {1}".format(e.errno, e.strerror))



