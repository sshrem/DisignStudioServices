import sys
sys.path.append('/Applications/Autodesk/FBX Python SDK/2016.1.1/lib/Python27')
import fbx

# Create an SDK manager
manager = fbx.FbxManager.Create()

# Create a scene
scene = fbx.FbxScene.Create(manager, "")

# Create an importer object
importer = fbx.FbxImporter.Create(manager, "")

# Path to the .obj file
milfalcon = "/Users/ohadbenporat/dev/python/B1.fbx"

# Specify the path and name of the file to be imported
importstat = importer.Initialize(milfalcon, -1)

importstat = importer.Import(scene)

# Create an exporter object
exporter = fbx.FbxExporter.Create(manager, "")

save_path = "/Users/ohadbenporat/dev/python/B1.obj"

# Specify the path and name of the file to be imported
exportstat = exporter.Initialize(save_path, -1)

exportstat = exporter.Export(scene)
