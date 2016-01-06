import os
import re
import setuptools
import setuptools.command.build_ext
import setuptools.command.install
import setuptools.dist

setuptools.dist.Distribution({
    "setup_requires": [
        "clint",
        "requests"
    ]
})

with open("prokaryote/__init__.py", "r") as fd:
    version = re.search(r"^__version__\s*=\s*['\"]([^'\"]*)['\"]", fd.read(), re.MULTILINE).group(1)

class Install(setuptools.command.install.install):
    def run(self):
        try:
            import clint.textui
            import requests
        except ImportError:
            raise ImportError

        directory = os.path.join("prokaryote")

        if not os.path.exists(directory):
            os.makedirs(directory)

        prokaryote = "{}/prokaryote.jar".format(directory)

        resource = "https://github.com/CellProfiler/prokaryote/releases/download/{tag}/prokaryote-{tag}.jar".format(tag=version)

        request = requests.get(resource, stream=True)

        with open(prokaryote, "wb") as f:
            total_length = int(request.headers.get("content-length"))

            chunks = clint.textui.progress.bar(request.iter_content(chunk_size=32768), expected_size=(total_length / 32768) + 1, hide=not self.verbose)

            for chunk in chunks:
                if chunk:
                    f.write(chunk)

                    f.flush()
        if self.distribution.package_data is None:
            self.distribution.package_data = {}
        self.distribution.package_data["prokaryote"] = ["prokaryote.jar"]
        setuptools.command.install.install.run(self)


setuptools.setup(
        author="Allen Goodman",
        author_email="allen.goodman@icloud.com",
        classifiers=[
            "Development Status :: 5 - Production/Stable",
            "Intended Audience :: Science/Research",
            "License :: OSI Approved :: BSD License",
            "Operating System :: OS Independent",
            "Programming Language :: C",
            "Programming Language :: C++",
            "Programming Language :: Cython",
            "Programming Language :: Python :: 2",
            "Programming Language :: Python :: 2.6",
            "Programming Language :: Python :: 2.7",
            "Topic :: Scientific/Engineering :: Bio-Informatics",
            "Topic :: Scientific/Engineering :: Image Recognition",
            "Topic :: Scientific/Engineering"
        ],
        cmdclass={
            "install": Install
        },
        include_package_data=True,
        license="BSD",
        name="prokaryote",
        packages=setuptools.find_packages(),
        setup_requires=[
            "clint",
            "requests"
        ],
        url="https://github.com/CellProfiler/prokaryote",
        version=version
)
