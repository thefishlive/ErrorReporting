package io.github.thefishlive.upload;

import io.github.thefishlive.upload.gist.GistUploadTarget;
import io.github.thefishlive.upload.pastebin.PastebinUploadTarget;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Constructor;

@RequiredArgsConstructor
public enum UploadTargets {

    GIST(GistUploadTarget.class),
    PASTEBIN(PastebinUploadTarget.class);

    private final Class<? extends UploadTarget> clazz;
    private Constructor<? extends UploadTarget> ctor;

    private Constructor<? extends UploadTarget> getCtor() throws NoSuchMethodException {
        if (ctor == null) {
            ctor = clazz.getConstructor();
        }

        return ctor;
    }

    public UploadTarget newHandler() throws ReflectiveOperationException {
        return getCtor().newInstance();
    }
}
