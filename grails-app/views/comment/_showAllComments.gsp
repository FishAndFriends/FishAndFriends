<g:each in="${comments}" var="com">
    <g:render template="showComment" model="[comment: com]"/>
</g:each>